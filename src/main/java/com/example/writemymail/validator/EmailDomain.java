package com.example.writemymail.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Constraint(validatedBy = EmailDomainValidator.class)
@Retention(RUNTIME)
public @interface EmailDomain {
    String message() default "Email должен быть с доменом mail.ru или yandex.ru";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
