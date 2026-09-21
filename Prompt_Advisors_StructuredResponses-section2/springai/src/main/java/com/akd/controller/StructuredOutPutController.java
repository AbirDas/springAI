package com.akd.controller;

import com.akd.advisor.TokenUsageAuditAdvisor;
import com.akd.model.CountryCities;
import com.openai.models.ChatModel;
import okhttp3.Response;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class StructuredOutPutController {

    private final ChatClient chatClient;
    private final OpenAiChatModel openAiChatModel;

    public StructuredOutPutController(ChatClient.Builder chatClientBuilder, OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
        this.chatClient = chatClientBuilder
                .defaultOptions(OpenAiChatOptions.builder().model(ChatModel.GPT_5_NANO.asString()))
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .build();
    }

    @GetMapping("/chat-bean")
    public ResponseEntity<CountryCities> chatBean(@RequestParam("message") String message) {
        CountryCities countryCities = chatClient
                .prompt()
                .user(message)
                .call().entity(CountryCities.class);

        return ResponseEntity.ok().body(countryCities);
    }

    @GetMapping("/chat-list")
    public ResponseEntity<List<String>> chatList(@RequestParam("message") String message) {
        List<String> countryCities = chatClient
                .prompt()
                .user(message)
                .call().entity(new ListOutputConverter());

        return ResponseEntity.ok().body(countryCities);
    }

    @GetMapping("/chat-map")
    public ResponseEntity<Map<String,Object>> chatMap(@RequestParam("message") String message) {
        Map<String, Object> countryCities = chatClient
                .prompt()
                .user(message)
                .call().entity(new MapOutputConverter());

        return ResponseEntity.ok().body(countryCities);
    }

    @GetMapping("/chat-bean-list")
    public ResponseEntity<List<CountryCities>> chatBeanList(@RequestParam("message") String message) {
        List<CountryCities> countryCities = chatClient
                .prompt()
                .user(message)
                .call().entity(new ParameterizedTypeReference<List<CountryCities>>(){
                });

        return ResponseEntity.ok().body(countryCities);
    }
}
