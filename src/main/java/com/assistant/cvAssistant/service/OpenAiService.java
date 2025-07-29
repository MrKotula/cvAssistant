package com.assistant.cvAssistant.service;

import com.assistant.cvAssistant.model.dto.GptMessageRequest;
import com.assistant.cvAssistant.model.dto.GptMessageResponse;

public interface OpenAiService {
    GptMessageResponse sendMessage(GptMessageRequest gptMessageRequest);
}
