package com.example.writemymail.service.email;


import com.example.writemymail.domain.dto.EmailRequest;
import com.example.writemymail.domain.dto.EmailResponse;
import com.example.writemymail.domain.entity.Email;

public interface EmailService {
    EmailResponse createEmail(EmailRequest emailRequest);
    void deleteEmail(EmailRequest emailRequest);
    void updateEmail(EmailRequest emailRequest);
    Email findByName(String name);
}
