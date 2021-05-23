package ru.geekbrains;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AmqpConsumerApp {
    private static final boolean NON_DURABLE = false;
    private static final String REQUEST_QUEUE_NAME = "requestQueue";
    private static final String RESPONSE_QUEUE_NAME = "responseQueue";

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(AmqpConsumerApp.class, args);
    }

    @Bean
    public Queue requestQueue() {
        return new Queue(REQUEST_QUEUE_NAME, NON_DURABLE);
    }


    @RabbitListener(queues = REQUEST_QUEUE_NAME)
    public void listen(String in) {
        System.out.println("Message read from request : " + in);
        rabbitTemplate.convertAndSend(RESPONSE_QUEUE_NAME, "RESPONSE " + in);
    }
}
