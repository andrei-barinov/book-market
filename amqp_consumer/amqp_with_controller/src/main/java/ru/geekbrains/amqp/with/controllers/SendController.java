package ru.geekbrains.amqp.with.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SendController {

    private RabbitTemplate rabbitTemplate;
    public static final String REQUEST_QUEUE_NAME = "requestQueue";

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public String send() {
        rabbitTemplate.convertAndSend(REQUEST_QUEUE_NAME, "This is request");
        return "Message sent";
    }
}
