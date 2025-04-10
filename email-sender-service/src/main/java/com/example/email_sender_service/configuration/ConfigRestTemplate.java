package com.example.email_sender_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigRestTemplate {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
