package com.example.writemymail.controller;

import com.example.writemymail.domain.dto.UserRequest;
import com.example.writemymail.domain.dto.UserResponse;
import com.example.writemymail.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{userId}")
    public UserResponse findById(@PathVariable UUID userId){
        return userService.findById(userId);
    }

    @PostMapping("/update")
    public UserResponse updateUser(@RequestBody UserRequest userRequest){
        return userService.updateUser(userRequest);
    }
}
