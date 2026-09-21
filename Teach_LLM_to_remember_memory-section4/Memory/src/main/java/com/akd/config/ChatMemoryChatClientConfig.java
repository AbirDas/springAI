package com.akd.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;

public class ChatMemoryChatClientConfig {

    @Bean("chatMemoryChatClient")
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }
}
