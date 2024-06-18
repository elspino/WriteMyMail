package com.example.writemymail.service.ai;

import com.example.writemymail.domain.dto.*;
import com.example.writemymail.service.prompt.PromptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
    private static final String CHAD_API_KEY = "chad-0c4850aeab6142d395d007a3bf8af447m075rd5j";
    private static final String URL = "https://ask.chadgpt.ru/api/public/gpt-3.5";
    private final PromptService promptService;

    public String parseResponse(ResponseEntity<String> response) {
        String jsonResponse = response.getBody();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AIResponse aiResponse = objectMapper.readValue(jsonResponse, AIResponse.class);
            return aiResponse.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String sendRequest(String prompt){
        Map<String, String> requestJson = new HashMap<>();
        requestJson.put("message", prompt);
        requestJson.put("api_key", CHAD_API_KEY);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        return parseResponse(response);
    }

    public static String[] parseGeneratedMessage(String email) {
        String[] result = new String[2];
        Pattern pattern = Pattern.compile("Subject: (.*?) Text: (.*)");
        Matcher matcher = pattern.matcher(email);
        result[0] = matcher.group(1).trim();
        result[1] = matcher.group(2).trim();
        return result;
    }

    @Override
    public GeneratePromptResponse generateMessage(GeneratePromptRequest promptRequest) {
        String response = sendRequest(promptService.createGeneratePrompt(promptRequest));
        String[] details = parseGeneratedMessage(response);
        return GeneratePromptResponse.builder()
                .subject(details[0])
                .text(details[1])
                .build();
    }

    @Override
    public UpgradePromptResponse upgradeMessage(UpgradePromptRequest promptRequest) {
        String response = sendRequest(promptService.createUpgradePrompt(promptRequest));
        return UpgradePromptResponse.builder()
                .text(response)
                .build();
    }

}
