package com.example.writemymail.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmailResponse {
    private UUID id;
    private String type;
    private String name;
    private String password;
    private UserResponse user;
}
