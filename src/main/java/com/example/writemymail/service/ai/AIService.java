package com.example.writemymail.service.ai;

import com.example.writemymail.domain.dto.GeneratePromptRequest;
import com.example.writemymail.domain.dto.GeneratedMessageResponse;
import com.example.writemymail.domain.dto.UpgradePromptRequest;
import com.example.writemymail.domain.dto.UpgradedMessageResponse;

public interface AIService {
    UpgradedMessageResponse upgradeMessage(UpgradePromptRequest promptRequest);
    GeneratedMessageResponse generateMessage(GeneratePromptRequest promptRequest);
}
