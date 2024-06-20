package com.example.writemymail.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailConfig {
    private final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @Bean
    public JavaMailSender getJavaMailSender() {
        mailSender.setHost("smtp");
        mailSender.setPort(465);
        mailSender.setUsername("username");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enabled", "false");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.enable", "true");

        return mailSender;
    }

    public void updateMailConfig(String username, String password, String host) {
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setHost("smtp."+host);
    }
}
