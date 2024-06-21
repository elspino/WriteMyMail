package com.example.writemymail.controller;

import com.example.writemymail.domain.dto.EmailResponse;
import com.example.writemymail.domain.dto.UserRequest;
import com.example.writemymail.domain.dto.UserResponse;
import com.example.writemymail.domain.entity.Email;
import com.example.writemymail.domain.entity.User;
import com.example.writemymail.mapper.EmailMapper;
import com.example.writemymail.mapper.UserMapper;
import com.example.writemymail.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final EmailMapper emailMapper;

    @GetMapping(value = "/{userId}")
    public UserResponse findById(@PathVariable UUID userId){
        User user = userService.findUserById(userId);
        return userMapper.userToResponse(user);
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody UserRequest userRequest){
        userService.updateUser(userRequest);
    }

    @GetMapping(value = "/{userId}/emails")
    public List<EmailResponse> getUserEmails(@PathVariable UUID userId){
        List<Email> emails = userService.getUserEmails(userId);
        return emailMapper.emailsToResponses(emails);
    }
}
