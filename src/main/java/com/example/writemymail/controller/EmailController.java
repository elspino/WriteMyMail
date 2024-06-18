package com.example.writemymail.controller;

import com.example.writemymail.domain.dto.EmailRequest;
import com.example.writemymail.domain.dto.EmailResponse;
import com.example.writemymail.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailController {
    private final EmailService emailService;
    
    @PostMapping("/create")
    public EmailResponse createEmail(@RequestBody EmailRequest emailRequest){
        return emailService.createEmail(emailRequest);
    }

    @DeleteMapping("/delete")
    public void deleteEmail(@RequestBody EmailRequest emailRequest){
        emailService.deleteEmail(emailRequest);
    }

    @PostMapping("/update")
    public EmailResponse updateEmail(@RequestBody EmailRequest emailRequest){
        return emailService.updateEmail(emailRequest);
    }
}