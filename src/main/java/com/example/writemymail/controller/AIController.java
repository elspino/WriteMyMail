package com.example.writemymail.controller;

import com.example.writemymail.domain.dto.GeneratePromptRequest;
import com.example.writemymail.domain.dto.GeneratePromptResponse;
import com.example.writemymail.domain.dto.UpgradePromptRequest;
import com.example.writemymail.domain.dto.UpgradePromptResponse;
import com.example.writemymail.service.ai.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ai")
public class AIController {
    private final AIService aiService;


    @GetMapping("/generate")
    public GeneratePromptResponse generateMessage(@RequestBody GeneratePromptRequest promptRequest) {
        return aiService.generateMessage(promptRequest);
    }

    @GetMapping("/upgrade")
    public UpgradePromptResponse upgradeMessage(@RequestBody UpgradePromptRequest promptRequest) {
        return aiService.upgradeMessage(promptRequest);
    }
}
