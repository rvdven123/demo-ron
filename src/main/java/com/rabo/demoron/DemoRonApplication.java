package com.rabo.demoron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoRonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoRonApplication.class, args);
    }
    
}
