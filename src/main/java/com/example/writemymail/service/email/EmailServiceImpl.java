package com.example.writemymail.service.email;

import com.example.writemymail.domain.dto.EmailRequest;
import com.example.writemymail.domain.dto.EmailResponse;
import com.example.writemymail.domain.entity.Email;
import com.example.writemymail.error.EmailAlreadyExistsException;
import com.example.writemymail.error.EmailNotFoundException;
import com.example.writemymail.error.UserNotFoundException;
import com.example.writemymail.mapper.EmailMapper;
import com.example.writemymail.repository.EmailRepository;
import com.example.writemymail.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final PasswordEncoder passwordEncoder;
    private final EmailRepository emailRepository;
    private final UserRepository userRepository;
    private final EmailMapper emailMapper;

    @Override
    public Email save(Email email) {return emailRepository.save(email);}

    @Override
    public void deleteEmail(EmailRequest emailRequest) {
        emailRepository.deleteById(emailRequest.getId());
    }

    @Override
    public EmailResponse updateEmail(EmailRequest emailRequest) {
        UUID emailId = emailRequest.getId();
        String emailName = emailRequest.getName();
        Email email = emailRepository.findById(emailId)
                .orElseThrow(() -> new EmailNotFoundException("Email not found with id: " + emailId));
        email.setName(emailName);
        email.setType(emailName.substring(emailName.indexOf("@") + 1));
        email.setPassword(passwordEncoder.encode(emailRequest.getPassword()));
        return emailMapper.emailToResponse(save(email));
    }

    @Override
    public Email findByName(String emailName) {
        return emailRepository.findByName(emailName)
                .orElseThrow(() -> new EmailNotFoundException("Email not found with username: " + emailName));
    }

    @Override
    public EmailResponse createEmail(EmailRequest emailRequest) {
        String name = emailRequest.getName();
        if (emailRepository.existsByName(name)){
            throw new EmailAlreadyExistsException("Такая почта уже добавлена");
        }
        UUID userId = emailRequest.getUser().getId();
        Email email = Email.builder()
                .name(name)
                .type(name.substring(name.indexOf("@") + 1))
                .password(passwordEncoder.encode(emailRequest.getPassword()))
                .user(userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId)))
                .build();
        return emailMapper.emailToResponse(save(email));
    }

}
