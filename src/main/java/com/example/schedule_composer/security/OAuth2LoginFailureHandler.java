package com.example.schedule_composer.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        System.out.println("Trying to redirect !!!!!!!!!!!!!!!!!!!!!!!!");
        if (exception instanceof OAuth2AuthenticationException) {

            response.sendRedirect("http://localhost:8080/login/oauth2/?error");
        } else {
            response.sendRedirect("/login?error");
        }
    }
}
