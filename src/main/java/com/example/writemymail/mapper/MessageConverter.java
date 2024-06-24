package com.example.writemymail.mapper;

import com.example.writemymail.domain.dto.AIResponse;
import com.example.writemymail.domain.dto.AIMessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MessageConverter {

    public String convertToMessage(ResponseEntity<String> response) {
        String jsonResponse = response.getBody();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AIResponse aiResponse = objectMapper.readValue(jsonResponse, AIResponse.class);
            return aiResponse.getMessage();
        } catch (JsonProcessingException e) {
            throw new IOException("Ошибка обработки JSON" + e.getMessage());
        }
    }
    public AIMessageResponse parseMessageToResponse(String message) {
        Pattern pattern = Pattern.compile("ТЕМА:\\s*(.*?)\\s+ТЕКСТ:\\s*(.*)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return AIMessageResponse.builder()
                    .subject(matcher.group(1))
                    .text(matcher.group(2))
                    .build();
        } else {
            throw new IllegalStateException("No match found");
        }

    }
}
