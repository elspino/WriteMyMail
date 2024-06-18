package com.example.writemymail.controller;

import com.example.writemymail.domain.dto.AuthenticationRequest;
import com.example.writemymail.domain.dto.AuthenticationResponse;
import com.example.writemymail.domain.dto.RegistrationRequest;
import com.example.writemymail.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public AuthenticationResponse authenticateUser(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return authService.authenticateUser(authenticationRequest);
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refreshToken(@RequestParam String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
    @PostMapping("/register")
    public void registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        authService.registerUser(registrationRequest);
    }

}
