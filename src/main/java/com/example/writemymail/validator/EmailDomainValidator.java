package com.example.writemymail.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailDomainValidator implements ConstraintValidator<EmailDomain, String> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w._%+-]+@(mail\\.ru|yandex\\.ru)$");

    @Override
    public void initialize(EmailDomain constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
