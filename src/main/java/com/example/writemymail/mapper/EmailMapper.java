package com.example.writemymail.mapper;

import com.example.writemymail.domain.dto.EmailRequest;
import com.example.writemymail.domain.dto.EmailResponse;
import com.example.writemymail.domain.entity.Email;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    Email requestToEmail(EmailRequest emailRequest);
    EmailResponse emailToResponse(Email email);
}
