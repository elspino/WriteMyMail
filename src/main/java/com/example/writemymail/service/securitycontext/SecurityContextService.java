package com.example.writemymail.service.securitycontext;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityContextService {
    void authenticateUserInContextHolder(UserDetails userDetails, HttpServletRequest request);
}
