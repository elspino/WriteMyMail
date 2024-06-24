package com.example.writemymail.service.email;

import com.example.writemymail.domain.dto.EmailRequest;
import com.example.writemymail.domain.dto.EmailResponse;
import com.example.writemymail.domain.entity.Email;
import com.example.writemymail.domain.entity.User;
import com.example.writemymail.error.EmailAlreadyExistsException;
import com.example.writemymail.error.EmailNotFoundException;
import com.example.writemymail.mapper.EmailMapper;
import com.example.writemymail.repository.EmailRepository;
import com.example.writemymail.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private final UserService userService;
    private final EmailMapper emailMapper;


    @Override
    public void updateEmail(EmailRequest emailRequest) {
        UUID emailId = emailRequest.getId();
        String emailName = emailRequest.getName();
        Email email = emailRepository.findById(emailId)
                .orElseThrow(() -> new EmailNotFoundException("Email not found with id: " + emailId));
        if (!email.getName().equals(emailName)){
            checkIfEmailExists(emailName);
        }
        email.setName(emailName);
        email.setType(getEmailType(emailName));
        email.setPassword(emailRequest.getPassword());
        save(email);
    }

    @Override
    public EmailResponse createEmail(EmailRequest emailRequest) {
        String emailName = emailRequest.getName();
        checkIfEmailExists(emailName);
        UUID userId = emailRequest.getUser().getId();
        User user = userService.findUserById(userId);
        Email email = Email.builder()
                .name(emailName)
                .type(getEmailType(emailName))
                .password(emailRequest.getPassword())
                .user(user)
                .build();
        return emailMapper.emailToResponse(save(email));
    }

    @Override
    public Email findByName(String emailName) {
        return emailRepository.findByName(emailName)
                .orElseThrow(() -> new EmailNotFoundException("Email not found with username: " + emailName));
    }

    @Override
    public void deleteEmail(EmailRequest emailRequest) {
        emailRepository.deleteById(emailRequest.getId());
    }

    private static String getEmailType(String emailName) {
        String domain = emailName.substring(emailName.indexOf("@") + 1);
        return domain.substring(0, domain.indexOf("."));
    }

    private Email save(Email email) {
        return emailRepository.save(email);
    }

    private void checkIfEmailExists(String emailName){
        if (emailRepository.existsByName(emailName)) {
            throw new EmailAlreadyExistsException("Такая почта уже добавлена");
        }
    }
}
