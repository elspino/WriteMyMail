package com.example.writemymail.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRequest {
    private UUID id;
    private String username;
    private String name;
    private String info;
}
