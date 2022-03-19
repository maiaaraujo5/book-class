package com.maiaaraujo5.bookclass.controller.config;

import com.maiaaraujo5.bookclass.controller.exception.InvalidTokenException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticatorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("Authorization");
        if (!isTokenValid(token)) {
            throw new InvalidTokenException("The provided token is invalid");
        }
    }

    private boolean isTokenValid(String token) {
        return token != null && !token.isEmpty() && !token.isBlank() && token.startsWith("Bearer ");
    }
}
