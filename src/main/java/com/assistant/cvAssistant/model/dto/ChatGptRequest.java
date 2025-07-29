package com.assistant.cvAssistant.model.dto;

import java.util.List;

public record ChatGptRequest(String model, List<ChatGptMessage> messages) {
}
