package com.example.writemymail.service.user;

import com.example.writemymail.domain.dto.UserRequest;
import com.example.writemymail.domain.dto.UserResponse;
import com.example.writemymail.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    UserResponse findById(UUID id);
    User createUser(User user);
    UserResponse updateUser(UserRequest userRequest);
    User save(User user);
    String getUsernameFromAuthorizedUser();
    boolean existByUsername(String username);

}
