package com.example.writemymail.service.ai;

import com.example.writemymail.mapper.MessageConverter;
import com.example.writemymail.domain.dto.*;
import com.example.writemymail.service.prompt.PromptService;
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

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {
    @Value("${ai-chat.chad.api-key}")
    private String api_key;
    @Value("${ai-chat.chad.url}")
    private String url;
    private final RestTemplate restTemplate;
    private final MessageConverter messageConverter;
    private final PromptService promptService;

    @Override
    public GeneratedMessageResponse generateMessage(GenerationPromptRequest promptRequest) {
        String prompt = promptService.createGenerationPrompt(promptRequest);
        String message = getMessageFromAI(prompt);
        return messageConverter.parseMessageToResponse(message);
    }

    @Override
    public UpgradedMessageResponse upgradeMessage(UpgradePromptRequest promptRequest) {
        String prompt = promptService.createUpgradePrompt(promptRequest);
        String response = getMessageFromAI(prompt);
        return UpgradedMessageResponse.builder()
                .text(response)
                .build();
    }

    private String getMessageFromAI(String prompt) {
        Map<String, String> requestJson = new HashMap<>();
        requestJson.put("message", prompt);
        requestJson.put("api_key", api_key);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return messageConverter.convertToMessage(response);
    }

}
