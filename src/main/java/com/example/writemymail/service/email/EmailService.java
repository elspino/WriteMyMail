package com.example.writemymail.service.email;


import com.example.writemymail.domain.dto.EmailRequest;
import com.example.writemymail.domain.dto.EmailResponse;
import com.example.writemymail.domain.entity.Email;

public interface EmailService {
    EmailResponse createEmail(EmailRequest emailRequest);
    Email save(Email email);
    void deleteEmail(EmailRequest emailRequest);
    EmailResponse updateEmail(EmailRequest emailRequest);
    Email findByName(String name);
}