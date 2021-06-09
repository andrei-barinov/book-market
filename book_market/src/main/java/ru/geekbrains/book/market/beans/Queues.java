package ru.geekbrains.book.market.beans;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;


public class Queues {

    private static final boolean NON_DURABLE = false;
    public static final String REQUEST_QUEUE_NAME = "requestQueue";
    private static final String RESPONSE_QUEUE_NAME = "responseQueue";

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
