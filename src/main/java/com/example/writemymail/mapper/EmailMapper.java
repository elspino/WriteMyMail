package com.example.writemymail.mapper;

import com.example.writemymail.domain.dto.EmailRequest;
import com.example.writemymail.domain.dto.EmailResponse;
import com.example.writemymail.domain.entity.Email;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    Email requestToEmail(EmailRequest emailRequest);
    EmailResponse emailToResponse(Email email);
    List<EmailResponse> emailsToResponses(List<Email> emails);
}
