package ru.geekbrains.amqp.with.controllers;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AmqpApp {

    private static final boolean NON_DURABLE = false;
    public static final String REQUEST_QUEUE_NAME = "requestQueue";
    private static final String RESPONSE_QUEUE_NAME = "responseQueue";

    public static void main(String[] args) {
        SpringApplication.run(AmqpApp.class, args);
    }

    @Bean
    public Queue requestQueue() {
        return new Queue(REQUEST_QUEUE_NAME, NON_DURABLE);
    }

    @Bean
    public Queue responseQueue() {
        return new Queue(RESPONSE_QUEUE_NAME, NON_DURABLE);
    }

    @RabbitListener(queues = RESPONSE_QUEUE_NAME)
    public void listen(String in) {
        System.out.println("Message read from response : " + in);
    }
}
