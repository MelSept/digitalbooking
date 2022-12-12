package com.digitalbooking.apilodgings.jwt;

import com.digitalbooking.apilodgings.response.ResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {


    ObjectMapper mapper;

    public AuthEntryPointJwt() {
        mapper = new ObjectMapper();
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());

        ResponseError responseError = new ResponseError("Unauthorized");
        responseError.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
        responseError.addHint(authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(mapper.writeValueAsString(responseError));
        response.getWriter().flush();
    }
}
