package com.akd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SpringAITestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAITestApplication.class,args);
    }
}
