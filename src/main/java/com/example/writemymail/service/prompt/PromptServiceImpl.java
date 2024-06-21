package com.example.writemymail.service.prompt;

import com.example.writemymail.domain.dto.GenerationPromptRequest;
import com.example.writemymail.domain.dto.UpgradePromptRequest;
import com.example.writemymail.repository.PromptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.writemymail.domain.enumerated.PromptType.GENERATE;
import static com.example.writemymail.domain.enumerated.PromptType.UPGRADE;

@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService{
    private final PromptRepository promptRepository;

    @Override
    public String createGenerationPrompt(GenerationPromptRequest promptRequest) {
        String prompt = promptRepository.findTextByType(GENERATE.name().toLowerCase());
        return String.format(prompt, promptRequest.getSender(), promptRequest.getSenderInfo(),
                promptRequest.getRecipientInfo(), promptRequest.getPurpose(), promptRequest.getRequirements());
    }

    @Override
    public String createUpgradePrompt(UpgradePromptRequest promptRequest) {
        String prompt = promptRepository.findTextByType(UPGRADE.name().toLowerCase());
        return String.format(prompt, promptRequest.getSender(), promptRequest.getSubject(),
                promptRequest.getBody());
    }
}
