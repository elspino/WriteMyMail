package com.example.writemymail.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIResponse {
    @JsonProperty("is_success")
    private boolean isSuccess;
    @JsonProperty("response")
    private String response;
    @JsonProperty("used_words_count")
    private int usedWordsCount;
    @JsonProperty("used_tokens_count")
    private int usedTokensCount;
}
