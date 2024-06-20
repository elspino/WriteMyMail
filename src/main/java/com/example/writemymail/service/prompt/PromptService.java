package com.example.writemymail.service.prompt;

import com.example.writemymail.domain.dto.GenerationPromptRequest;
import com.example.writemymail.domain.dto.UpgradePromptRequest;

public interface PromptService {
    String createGenerationPrompt(GenerationPromptRequest promptRequest);
    String createUpgradePrompt(UpgradePromptRequest promptRequest);
}
