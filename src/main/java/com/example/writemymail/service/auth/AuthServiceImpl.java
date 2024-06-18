package com.example.writemymail.service.auth;

import com.example.writemymail.domain.dto.AuthenticationRequest;
import com.example.writemymail.domain.dto.AuthenticationResponse;
import com.example.writemymail.domain.dto.RegistrationRequest;
import com.example.writemymail.domain.entity.User;
import com.example.writemymail.domain.enumerated.Role;
import com.example.writemymail.error.NotValidTokenException;
import com.example.writemymail.service.user.UserService;
import com.example.writemymail.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
        UserDetails userDetails = userService.loadUserByUsername(
                authenticationRequest.getUsername());

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Неверный пароль");
        }


        return new AuthenticationResponse(jwtTokenUtil.generateRefreshToken(userDetails),
                jwtTokenUtil.generateAccessToken(userDetails));
    }
    @Override
    public AuthenticationResponse refreshToken(String refreshToken) {
        if (!jwtTokenUtil.isRefreshToken(refreshToken)) {
            throw new NotValidTokenException("Это не рефреш токен");
        }
        UserDetails userDetails = jwtTokenUtil.findUserDetailsByJwt(refreshToken);

        return new AuthenticationResponse(jwtTokenUtil.generateRefreshToken(userDetails),
                jwtTokenUtil.generateAccessToken(userDetails));
    }
    @Override
    public void registerUser(RegistrationRequest registrationRequest) {

        User user = User.builder()
                .username(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(Role.USER)
                .build();

        userService.createUser(user);
    }

}
