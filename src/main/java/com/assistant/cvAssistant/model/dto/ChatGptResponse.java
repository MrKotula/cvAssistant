package com.assistant.cvAssistant.model.dto;

import java.util.List;

public record ChatGptResponse(List<Choice> choices) {
}
