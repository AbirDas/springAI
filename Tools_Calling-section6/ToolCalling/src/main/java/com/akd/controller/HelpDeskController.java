package com.akd.controller;

import com.akd.tools.HelpDeskTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai/tools")
public class HelpDeskController {

    private final ChatClient chatClient;
    private final HelpDeskTools helpDeskTools;

    public HelpDeskController(@Qualifier("helpDeskChatClient") ChatClient chatClient,
                              HelpDeskTools helpDeskTools) {
        this.chatClient = chatClient;
        this.helpDeskTools = helpDeskTools;
    }

    @GetMapping("/help-desk")
    public ResponseEntity<String> helpDesk(@RequestHeader("username") String username,
                                           @RequestParam("message") String message) {
        String answer = chatClient.prompt()
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID,username))
                .user(message)
                .tools(helpDeskTools)
                .toolContext(Map.of("username",username))
                .call().content();
        return ResponseEntity.ok(answer);
    }
}
