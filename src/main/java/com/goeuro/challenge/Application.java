package com.goeuro.challenge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@SpringBootApplication
public class Application implements HealthIndicator {

    public static void main(String[] args) {
        log.info("Args", args[0]);
        SpringApplication.run(Application.class, args);
    }

    @Override
    public Health health() {
        return Health.up().build();
    }
}
