package com.assistant.cvAssistant.exception;

public class OpenAIApiResponseException extends OpenAIApiException {

    public OpenAIApiResponseException(String errorMessage) {
       super(errorMessage);
    }
}
