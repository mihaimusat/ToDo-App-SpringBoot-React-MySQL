package com.endava.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MvcConfiguration {

    @Bean
    public CorsFilter addCorsFilter() {
        return new CorsFilter();
    }
}
