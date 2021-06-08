package ru.geekbrains.book.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.book.market.entities.User;
import ru.geekbrains.book.market.services.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String findUserNameByLogin(@RequestBody Principal principal){
        User user = userService.findByUserLogin(principal.getName()).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь с данным логин не найден"));
        return user.getUserFirstName();
    }
}
