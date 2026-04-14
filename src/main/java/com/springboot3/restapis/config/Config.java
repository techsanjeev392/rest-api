package com.springboot3.restapis.config;


import com.springboot3.restapis.config.message.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MessageService messageService() {
        return new MessageService();
    }

}
