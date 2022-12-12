package com.digitalbooking.apilodgings.jwt;

import com.digitalbooking.apilodgings.response.ResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HandlerAccessDeniedException implements AccessDeniedHandler {

    ObjectMapper mapper;

    public HandlerAccessDeniedException() {
        mapper = new ObjectMapper();
    }

    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedException.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        logger.error("Access denied: {}", accessDeniedException.getMessage());

        ResponseError responseError = new ResponseError("Access Denied");
        responseError.setStatusCode(HttpServletResponse.SC_FORBIDDEN);
        responseError.addHint("You do not have permissions to access this resource.");

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(mapper.writeValueAsString(responseError));
        response.getWriter().flush();
    }
}
