package com.akd.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai/tools")
public class TimeController {

    private final ChatClient chatClient;

    public TimeController(@Qualifier("timeChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/locat-time")
    public ResponseEntity<String> localTime(@RequestHeader("username") String username,
                                            @RequestParam("message") String message) {
        String answer = chatClient.prompt()
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID,username))
                .call().content();
        return ResponseEntity.ok(answer);
    }
}
