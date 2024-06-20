package com.example.writemymail.controller;

import com.example.writemymail.domain.dto.UserRequest;
import com.example.writemymail.domain.dto.UserResponse;
import com.example.writemymail.domain.entity.User;
import com.example.writemymail.mapper.UserMapper;
import com.example.writemymail.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(value = "/{userId}")
    public UserResponse findById(@PathVariable UUID userId){
        User user = userService.findUserById(userId);
        return userMapper.userToResponse(user);
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody UserRequest userRequest){
        userService.updateUser(userRequest);
    }
}
