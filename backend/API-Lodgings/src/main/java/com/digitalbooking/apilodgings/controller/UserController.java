package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.dto.user.CreateUserAdminDTO;
import com.digitalbooking.apilodgings.dto.user.CreateUserDTO;
import com.digitalbooking.apilodgings.entity.Role;
import com.digitalbooking.apilodgings.entity.User;
import com.digitalbooking.apilodgings.enums.ERole;
import com.digitalbooking.apilodgings.exception.BadRequestException;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.jwt.JwtUtils;
import com.digitalbooking.apilodgings.jwt.payload.JwtResponse;
import com.digitalbooking.apilodgings.jwt.payload.SignInRequest;
import com.digitalbooking.apilodgings.repository.IRoleRepository;
import com.digitalbooking.apilodgings.repository.IUserRepository;
import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.response.ResponseError;
import com.digitalbooking.apilodgings.service.user.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/auth")
@Tag(name = "User", description = "Endpoint to management users actions.")
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Bearer Authentication",
        description = "Use JWT for authenticate",
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class UserController {

    AuthenticationManager authenticationManager;
    IUserRepository userRepository;
    IRoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtUtils jwtUtils;


    @Autowired
    public UserController(AuthenticationManager authenticationManager,
                          IUserRepository userRepository,
                          IRoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(path = "/signIn")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody SignInRequest signIn) throws BadRequestException, NotFoundException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        User userFound = userRepository.findByUsernameIgnoreCaseOrEmailEquals(signIn.getUsername(), signIn.getUsername()).orElse(null);

        if (userFound == null) {
            ResponseError responseError =
                    new ResponseError(String.format("Authentication Error: Not found register user with email or username: %s", signIn.getUsername()));
            responseError.addHint("Enter valid username or email.");
            throw new NotFoundException(responseError);
        }

        String passwordSaved = userFound.getPassword();

        if (!passwordEncoder.matches(signIn.getPassword(), passwordSaved)) {
            ResponseError responseError =
                    new ResponseError("Authentication Error: Invalid Password");
            responseError.addHint("Verify your password and enter the correct password.");
            throw new BadRequestException(responseError);
        }


        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userFound.getUsername(), signIn.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        var authorities = userDetails.getAuthorities().stream().toList();
        String grantedAuthority = authorities.size() != 0 ? authorities.get(0).getAuthority() : new SimpleGrantedAuthority("").getAuthority();

        String role = switch (grantedAuthority) {
            case "ROLE_USER" -> "user";
            case "ROLE_ADMIN" -> "admin";
            default -> "";
        };

        return new ResponseEntity<>(new JwtResponse(
                userDetails.getId(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                jwt,
                "Bearer",
                role
        ), headers, HttpStatus.OK);
    }


    @Operation(summary = "Endpoint to register a new user", description = "Description")
    @ApiResponses(value = {
            @ApiResponse(description = "Successful user creation.", responseCode = "201", content = {@Content(schema = @Schema(implementation = Response.class))})
    })
    @PostMapping(path = "/signUp/admin")
    public ResponseEntity<Response> createUserAdmin(@Valid @RequestBody CreateUserAdminDTO createUserAdminDTO) throws BadRequestException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if (userRepository.findByUsernameIgnoreCase(createUserAdminDTO.getUsername()).isPresent()) {
            ResponseError responseError = new ResponseError("Error: Username is already taken!");
            responseError.addHint("Choose different username.");
            throw new BadRequestException(responseError);
        }

        if (userRepository.existsByEmail(createUserAdminDTO.getEmail())) {
            ResponseError responseError = new ResponseError("Error: Email is already in use!");
            responseError.addHint("Choose different Email.");
            throw new BadRequestException(responseError);
        }

        User user = new User();
        user.setFirstName(createUserAdminDTO.getFirstName());
        user.setLastName(createUserAdminDTO.getLastName());
        user.setUsername(createUserAdminDTO.getUsername());
        user.setEmail(createUserAdminDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserAdminDTO.getPassword()));
        user.setCity(createUserAdminDTO.getCity());
        Role role = new Role();
        role.setTitle(createUserAdminDTO.getRole());
        user.setRole(role);

        userRepository.save(user);

        Response response = new Response("User Admin successfully created.");

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }


    @Operation(summary = "Endpoint to register a new user", description = "Description")
    @ApiResponses(value = {
            @ApiResponse(description = "Successful user creation.", responseCode = "201", content = {@Content(schema = @Schema(implementation = Response.class))})
    })
    @PostMapping(path = "/signUp")
    public ResponseEntity<Response> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) throws BadRequestException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if (userRepository.findByUsernameIgnoreCase(createUserDTO.getUsername()).isPresent()) {
            ResponseError responseError = new ResponseError("Error: Username is already taken!");
            responseError.addHint("Choose different username.");
            throw new BadRequestException(responseError);
        }

        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            ResponseError responseError = new ResponseError("Error: Email is already in use!");
            responseError.addHint("Choose different Email.");
            throw new BadRequestException(responseError);
        }

        User user = new User();
        user.setFirstName(createUserDTO.getFirstName());
        user.setLastName(createUserDTO.getLastName());
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setCity(createUserDTO.getCity());
        Role role = roleRepository.findByTitle(ERole.ROLE_USER).get();
        user.setRole(role);

        userRepository.save(user);

        Response response = new Response("User successfully created.");

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }


    @Operation(summary = "Endpoint to register a new user", description = "Description")
    @ApiResponses(value = {
            @ApiResponse(description = "Successful user creation.", responseCode = "201", content = {@Content(schema = @Schema(implementation = Response.class))})
    })
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Response> deleteUserById(
            @Valid
            @NotNull(message = "The 'userId' cannot be null.")
            @PathVariable Integer userId) throws BadRequestException {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        userRepository.deleteById(userId);

        Response response = new Response("User successfully deleted.");


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Operation(summary = "Endpoint to register a new user", description = "Description")
    @ApiResponses(value = {
            @ApiResponse(description = "Successful user creation.", responseCode = "201", content = {@Content(schema = @Schema(implementation = Response.class))})
    })
    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> findUserById(
            @Valid
            @NotNull(message = "The 'userId' cannot be null.")
            @PathVariable Integer userId) throws BadRequestException {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        User userFound = userRepository.findById(userId).orElse(null);

        Response response = new Response("User successfully deleted.");

        return new ResponseEntity<>(userFound, headers, HttpStatus.OK);
    }

    @GetMapping(name = "Find All Categories", path = {"/"})
    @Operation(method = "GET", summary = "Get All Categories")
    @SecurityRequirement(name = "Bearer Authentication")
    void GetAllUsers() {

    }
}
