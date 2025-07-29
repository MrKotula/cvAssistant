package com.assistant.cvAssistant.exception;

public class OpenAIApiConnectionException extends OpenAIApiException {

    public OpenAIApiConnectionException(String errorMessage) {
        super(errorMessage);
    }
}
