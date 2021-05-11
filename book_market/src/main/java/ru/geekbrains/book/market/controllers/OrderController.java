package ru.geekbrains.book.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.book.market.dto.OrderDto;
import ru.geekbrains.book.market.entities.Order;
import ru.geekbrains.book.market.entities.User;
import ru.geekbrains.book.market.exception.OrderNotFoundException;
import ru.geekbrains.book.market.services.CartService;
import ru.geekbrains.book.market.services.OrderService;
import ru.geekbrains.book.market.services.UserService;


import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrderFromCart(Principal principal, @RequestParam String address) {
        Order order = orderService.createFromUserCart(principal.getName(), address);
        User user = userService.findByUserLogin(principal.getName()).orElseThrow();
        cartService.clearCart(user.getUserId());
        return new OrderDto(order);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id).orElseThrow(() -> new OrderNotFoundException(
                String.format("Не найден заказ с id %s", id)
        ));
        return new OrderDto(order);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findAllByOwner(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
//        return orderService.findAllByOwner(principal.getName());
    }
}
