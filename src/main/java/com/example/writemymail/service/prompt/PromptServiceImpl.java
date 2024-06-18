package com.example.writemymail.service.prompt;

import com.example.writemymail.domain.dto.GeneratePromptRequest;
import com.example.writemymail.domain.dto.UpgradePromptRequest;
import com.example.writemymail.repository.PromptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService{
    private final PromptRepository promptRepository;

    @Override
    public String createGeneratePrompt(GeneratePromptRequest promptRequest) {
        return String.format(promptRepository.findTextByName("generate"),
                promptRequest.getSender(), promptRequest.getSenderInfo(), promptRequest.getRecipientInfo(),
                promptRequest.getPurpose(), promptRequest.getRequirements()
        );
    }

    @Override
    public String createUpgradePrompt(UpgradePromptRequest promptRequest) {
        return String.format(promptRepository.findTextByName("upgrade"),
                promptRequest.getSender(), promptRequest.getSubject(), promptRequest.getBody(), promptRequest.getPurpose()
        );
    }
}
