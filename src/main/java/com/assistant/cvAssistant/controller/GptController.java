package com.assistant.cvAssistant.controller;

import com.assistant.cvAssistant.model.dto.GptMessageRequest;
import com.assistant.cvAssistant.model.dto.GptMessageResponse;
import com.assistant.cvAssistant.service.OpenAiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gpt")
public class GptController {

    private final OpenAiService openAiService;

    public GptController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("ask")
    public GptMessageResponse ask(@RequestBody GptMessageRequest gptMessageRequest) {
        return openAiService.sendMessage(gptMessageRequest);
    }
}
