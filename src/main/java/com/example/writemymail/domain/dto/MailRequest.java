package com.example.writemymail.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequest {
    String From;
    String To;
    String Subject;
    String Text;
}
