package ru.geekbrains.eureka.client.books.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {
    @GetMapping
    public String writeWelcome(){
        return "Welcome to bookstore";
    }
}
