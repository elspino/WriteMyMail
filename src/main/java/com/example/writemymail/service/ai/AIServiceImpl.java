package com.example.writemymail.service.ai;

import com.example.writemymail.domain.dto.*;
import com.example.writemymail.service.prompt.PromptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService{
    private final RestTemplate restTemplate;

    @Value("${ai-chat.chad.api-key}")
    private String api_key;

    @Value("${ai-chat.chad.url}")
    private String url;

    private final PromptService promptService;

    @Override
    public GeneratedMessageResponse generateMessage(GeneratePromptRequest promptRequest) {
        String prompt = promptService.createGeneratePrompt(promptRequest);
        String response = getResponseFromAI(prompt);
        return getResponseFromAIMessage(response);
    }

    @Override
    public UpgradedMessageResponse upgradeMessage(UpgradePromptRequest promptRequest) {
        String response = getResponseFromAI(promptService.createUpgradePrompt(promptRequest));
        return UpgradedMessageResponse.builder()
                .text(response)
                .build();
    }

    private String getMessageFromAIResponse(ResponseEntity<String> response) {
        String jsonResponse = response.getBody();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AIResponse aiResponse = objectMapper.readValue(jsonResponse, AIResponse.class);
            return aiResponse.getMessage();
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Ошибка обработки JSON" + e.getMessage());
        }
    }

    private String getResponseFromAI(String prompt){
        Map<String, String> requestJson = new HashMap<>();
        requestJson.put("message", prompt);
        requestJson.put("api_key", api_key);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return getMessageFromAIResponse(response);
    }

    private GeneratedMessageResponse getResponseFromAIMessage(String message) {
        Pattern pattern = Pattern.compile("ТЕМА: (.*?)\\s+ТЕКСТ: (.*)\\s", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(message);
        return GeneratedMessageResponse.builder()
                .subject(matcher.group(1))
                .text(matcher.group(2))
                .build();

    }
}
