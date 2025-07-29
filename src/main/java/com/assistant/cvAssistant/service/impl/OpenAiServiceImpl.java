package com.assistant.cvAssistant.service.impl;

import com.assistant.cvAssistant.exception.OpenAIApiConnectionException;
import com.assistant.cvAssistant.exception.OpenAIApiResponseException;
import com.assistant.cvAssistant.model.dto.ChatGptMessage;
import com.assistant.cvAssistant.model.dto.ChatGptRequest;
import com.assistant.cvAssistant.model.dto.ChatGptResponse;
import com.assistant.cvAssistant.model.dto.GptMessageRequest;
import com.assistant.cvAssistant.model.dto.GptMessageResponse;
import com.assistant.cvAssistant.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    private static final String USER_ROLE = "user";
    private static final String GPT_MODEL = "gpt-3.5-turbo";
    private static final String GPT_EMPTY_RESPONSE_EXCEPTION = "OpenAI API returned empty response.";
    private static final String GPT_CONNECTION_EXCEPTION = "Failed to call OpenAI API: ";
    private static final byte GPT_FIRST_ANSWER = 0;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public OpenAiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public GptMessageResponse sendMessage(GptMessageRequest gptMessageRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            ChatGptMessage message = new ChatGptMessage(USER_ROLE, gptMessageRequest.userMessage());
            ChatGptRequest request = new ChatGptRequest(GPT_MODEL, List.of(message));

            HttpEntity<ChatGptRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<ChatGptResponse> response = restTemplate.exchange(
                    apiUrl, HttpMethod.POST, entity, ChatGptResponse.class
            );

            ChatGptResponse body = response.getBody();

            if(body == null || body.choices() == null || body.choices().isEmpty()) {
                throw new OpenAIApiResponseException(GPT_EMPTY_RESPONSE_EXCEPTION);
            }

            return new GptMessageResponse(body
                    .choices()
                    .get(GPT_FIRST_ANSWER)
                    .message()
                    .content());

        }  catch (Exception e) {
            throw new OpenAIApiConnectionException(GPT_CONNECTION_EXCEPTION + e);
        }
    }
}
