package com.example.writemymail.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpgradePromptRequest {
    String sender;
    String subject;
    String body;
}
