package com.example.writemymail.service.user;

import com.example.writemymail.domain.dto.UserRequest;
import com.example.writemymail.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    User createUser(User user);
    void updateUser(UserRequest userRequest);
    User save(User user);
    User findUserById(UUID userId);
    User findUserByUsername(String username);
}
