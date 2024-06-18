package com.example.writemymail.service.prompt;

import com.example.writemymail.domain.dto.GeneratePromptRequest;
import com.example.writemymail.domain.dto.UpgradePromptRequest;

public interface PromptService {
    String createGeneratePrompt(GeneratePromptRequest promptRequest);
    String createUpgradePrompt(UpgradePromptRequest promptRequest);
}
