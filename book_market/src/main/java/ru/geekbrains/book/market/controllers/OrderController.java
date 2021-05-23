package ru.geekbrains.book.market.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
import springfox.documentation.spring.web.json.Json;


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
    private final RabbitTemplate rabbitTemplate;
    public static final String REQUEST_QUEUE_NAME = "requestQueue";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrderFromCart(Principal principal, @RequestParam String address) throws JsonProcessingException {
        Order order = orderService.createFromUserCart(principal.getName(), address);
        User user = userService.findByUserLogin(principal.getName()).orElseThrow();
        cartService.clearCart(user.getUserId());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new OrderDto(order));
        rabbitTemplate.convertAndSend(REQUEST_QUEUE_NAME, json);
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
