package com.example.writemymail.config;


import com.example.writemymail.error.NotValidTokenException;
import com.example.writemymail.error.resolver.ExceptionResolver;
import com.example.writemymail.service.securitycontext.SecurityContextService;
import com.example.writemymail.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final JwtTokenUtil jwtTokenUtil;
    private final SecurityContextService securityContextService;
    private final ExceptionResolver exceptionResolver;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(BEARER_PREFIX.length());

        if(!jwtTokenUtil.isRefreshToken(jwt)) {
            UserDetails userDetails = jwtTokenUtil.findUserDetailsByJwt(jwt);
            securityContextService.authenticateUserInContextHolder(userDetails, request);
        } else  {
            exceptionResolver.handleNotValidTokenException(request,response,
                    new NotValidTokenException("Это не аксес токен"));
            return;
        }

        filterChain.doFilter(request, response);
    }
}

