package com.digitalbooking.apilodgings.security;

import com.digitalbooking.apilodgings.exception.HandlerAccessDeniedException;
import com.digitalbooking.apilodgings.jwt.AuthEntryPointJwt;
import com.digitalbooking.apilodgings.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final HandlerAccessDeniedException accessDeniedHandler;


    @Autowired
    public WebSecurityConfig(@Qualifier("UserDetailsServiceImpl") UserDetailsService userDetailsService, AuthEntryPointJwt unauthorizedHandler, HandlerAccessDeniedException accessDeniedHandler) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.accessDeniedHandler = accessDeniedHandler;
    }


    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Allowed API Documentation EndPoints
                .authorizeRequests()
                .antMatchers("swagger-ui/**").permitAll()
                .antMatchers("swagger-ui**").permitAll()
                .antMatchers("/api-docs/**").permitAll()
                .antMatchers("/api-docs**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()

                // Allowed EndPoints
                // Auth Users EndPoint
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/product/**").permitAll()
                .antMatchers("/feature/**").permitAll()
                .antMatchers("/category/**").permitAll()
                .antMatchers("/category/title/").permitAll()
                .antMatchers("/city/**").permitAll()
                .antMatchers("/place/**").permitAll()
                .antMatchers("/reservation/**").permitAll()
                .and().authorizeRequests()
                // Authenticated Product EndPoint
                .antMatchers("/digitalbooking/lodgings/api/v1/**").permitAll().anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
