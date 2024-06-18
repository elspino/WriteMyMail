package com.example.writemymail.service.mail;

import com.example.writemymail.domain.dto.MailRequest;

public interface MailService {
    void sendMessage(MailRequest mailRequest);
}
