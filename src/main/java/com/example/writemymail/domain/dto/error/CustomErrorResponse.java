package com.example.writemymail.domain.dto.error;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

@RequiredArgsConstructor
public class CustomErrorResponse implements ErrorResponse {
    private final int statusCode;
    private final String message;
    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatus.valueOf(statusCode);
    }

    @Override
    public ProblemDetail getBody() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(statusCode),message);
    }
}
