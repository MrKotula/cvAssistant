package com.assistant.cvAssistant.exception;

public class OpenAIApiException extends RuntimeException {

    private final String errorMessage;

    public OpenAIApiException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
