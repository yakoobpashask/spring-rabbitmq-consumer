package com.evoke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRabbitMQConsumerApplication {

    public static void main(String[] args) {

        SpringApplication.run(new Object[] {SpringRabbitMQConsumerApplication.class}, args);
    }
}
