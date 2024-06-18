package com.example.writemymail.service.mail;

import com.example.writemymail.config.MailConfig;
import com.example.writemymail.domain.dto.MailRequest;
import com.example.writemymail.domain.entity.Email;
import com.example.writemymail.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
    private final JavaMailSender mailSender;
    private final MailConfig mailConfig;
    private final EmailService emailService;

    public SimpleMailMessage createMessage(MailRequest mailRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailRequest.getFrom());
        message.setTo(mailRequest.getTo());
        message.setSubject(mailRequest.getSubject());
        message.setText(mailRequest.getText());
        return message;
    }

    @Override
    public void sendMessage(MailRequest mailRequest){
        Email email = emailService.findByName(mailRequest.getFrom());
        mailConfig.updateMailConfig(email.getName(), email.getPassword(), email.getType());
        SimpleMailMessage message = createMessage(mailRequest);
        mailSender.send(message);
    }

}
