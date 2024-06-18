package com.example.writemymail.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneratePromptRequest {
    String sender;
    String senderInfo;
    String recipientInfo;
    String purpose;
    String requirements;
}
