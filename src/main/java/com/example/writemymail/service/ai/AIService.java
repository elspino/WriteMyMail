package com.example.writemymail.service.ai;

import com.example.writemymail.domain.dto.GenerationPromptRequest;
import com.example.writemymail.domain.dto.AIMessageResponse;
import com.example.writemymail.domain.dto.UpgradePromptRequest;

public interface AIService {
    AIMessageResponse upgradeMessage(UpgradePromptRequest promptRequest);
    AIMessageResponse generateMessage(GenerationPromptRequest promptRequest);
}
