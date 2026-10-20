package com.akd.controller;

import com.akd.advisor.TokenUsageAuditAdvisor;
import com.openai.models.ChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                //.advisors(new TokenUsageAuditAdvisor())
                //.options(OpenAiChatOptions.builder().model(ChatModel.GPT_5_NANO.asString()).temperature(0.7))
                .system("""
                        You are an internal IT helpdesk assistant. Your role is to assist\s
                        employees with IT-related issues such as resetting passwords,\s
                        unlocking accounts, and answering questions related to IT policies.
                        If a user requests help with anything outside of these\s
                        responsibilities, respond politely and inform them that you are\s
                        only able to assist with IT support tasks within your defined scope.
                        """)
                .user(message)
                .call()
                .content();
    }
}
