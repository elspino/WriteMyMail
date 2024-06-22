package com.example.writemymail.controller;

import com.example.writemymail.domain.dto.MailRequest;
import com.example.writemymail.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody MailRequest mailRequest){
        mailService.sendMessage(mailRequest);
    }
}
