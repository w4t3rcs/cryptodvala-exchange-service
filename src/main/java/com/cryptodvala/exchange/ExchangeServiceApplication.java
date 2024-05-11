package com.cryptodvala.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestClient;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class ExchangeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeServiceApplication.class, args);
    }

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
