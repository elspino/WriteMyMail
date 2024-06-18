package com.example.writemymail.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class GeneratePromptResponse {
    String subject;
    String text;
}
