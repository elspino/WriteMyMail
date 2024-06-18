package com.example.writemymail.controller.advice;

import com.example.writemymail.domain.dto.error.CustomErrorResponse;
import com.example.writemymail.domain.dto.error.ValidationErrorResponse;
import com.example.writemymail.domain.dto.error.Violation;
import com.example.writemymail.error.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException e
    ) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }
    @ResponseBody
    @ExceptionHandler(NotValidTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorResponse handleNotValidTokenException(NotValidTokenException e) {
        return new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(CredentialsAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorResponse handleCredentialsAlreadyExistsException(CredentialsAlreadyExistsException e) {
        return new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorResponse handleBadCredentialsException(BadCredentialsException e) {
        return new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorResponse handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorResponse handleEmailAlreadyExistsException(EmailAlreadyExistsException e){
        return new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorResponse handleEmailNotFoundException(EmailNotFoundException e){
        return new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorResponse handleUserNotFoundException(UserNotFoundException e){
        return new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}