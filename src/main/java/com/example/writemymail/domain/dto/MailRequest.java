package com.example.writemymail.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequest {
    String from;
    String to;
    String subject;
    String text;
}
