package com.example.writemymail.service.ai;

import com.example.writemymail.domain.dto.GeneratePromptRequest;
import com.example.writemymail.domain.dto.GeneratePromptResponse;
import com.example.writemymail.domain.dto.UpgradePromptRequest;
import com.example.writemymail.domain.dto.UpgradePromptResponse;

public interface AIService {
    UpgradePromptResponse upgradeMessage(UpgradePromptRequest promptRequest);
    GeneratePromptResponse generateMessage(GeneratePromptRequest promptRequest);
}
