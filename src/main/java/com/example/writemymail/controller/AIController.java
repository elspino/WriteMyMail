package com.example.writemymail.controller;

import com.example.writemymail.domain.dto.GenerationPromptRequest;
import com.example.writemymail.domain.dto.GeneratedMessageResponse;
import com.example.writemymail.domain.dto.UpgradePromptRequest;
import com.example.writemymail.domain.dto.UpgradedMessageResponse;
import com.example.writemymail.service.ai.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ai")
public class AIController {
    private final AIService aiService;


    @PostMapping("/generate")
    public GeneratedMessageResponse generateMessage(@RequestBody GenerationPromptRequest promptRequest) {
        return aiService.generateMessage(promptRequest);
    }

    @PostMapping("/upgrade")
    public UpgradedMessageResponse upgradeMessage(@RequestBody UpgradePromptRequest promptRequest) {
        return aiService.upgradeMessage(promptRequest);
    }
}
