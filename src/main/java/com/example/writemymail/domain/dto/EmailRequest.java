package com.example.writemymail.domain.dto;

import com.example.writemymail.validator.EmailDomain;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Getter
@Setter
public class EmailRequest {
    private UUID id;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    @EmailDomain
    private String name;
    private String password;
    private UserRequest user;
}
