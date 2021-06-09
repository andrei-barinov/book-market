package ru.geekbrains.book.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.book.market.entities.User;
import ru.geekbrains.book.market.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping
    public User findUserNameByLogin(@RequestParam String login){
        User user = userService.findByUserLogin(login).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь с данным логин не найден"));
        String userName = user.getUserFirstName() + " " + user.getUserLastName();
        user.setLogin(userName);
        return user;
    }
}
