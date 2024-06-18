package com.example.writemymail.service.auth;

import com.example.writemymail.domain.dto.AuthenticationRequest;
import com.example.writemymail.domain.dto.AuthenticationResponse;
import com.example.writemymail.domain.dto.RegistrationRequest;

public interface AuthService {
    AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest);
    AuthenticationResponse refreshToken(String refreshToken);
    void registerUser(RegistrationRequest registrationRequest);

}
