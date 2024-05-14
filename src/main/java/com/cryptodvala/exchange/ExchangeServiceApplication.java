package com.cryptodvala.exchange;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@EnableAsync
@EnableScheduling
@EnableAdminServer
@SpringBootApplication
public class ExchangeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeServiceApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(RedisConfiguration redisConfiguration) {
        return new LettuceConnectionFactory(redisConfiguration, LettuceClientConfiguration.builder().useSsl().build());
    }

    @Bean
    public RedisConfiguration redisConfiguration(@Value("${REDIS_HOST}") String redisHost, @Value("${REDIS_PORT}") int redisPort, @Value("${REDIS_PASSWORD}") String redisPassword) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setPassword(redisPassword);
        return redisStandaloneConfiguration;
    }
}
