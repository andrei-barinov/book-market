package ru.geekbrains.book.market.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.book.market.entities.Cart;
import ru.geekbrains.book.market.entities.Order;
import ru.geekbrains.book.market.entities.User;
import ru.geekbrains.book.market.exception.UserNotFoundException;
import ru.geekbrains.book.market.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final CartService cartService;

    @Transactional
    public Order createFromUserCart(String username, String address) {
        User user = userService.findByUserLogin(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с данным login не найден"));
        Cart cart = cartService.findCartByOwnerId(user.getUserId());
        if (cart.getCartItems().isEmpty()) throw new RuntimeException("Корзина пустая");
        Order order = new Order(cart, address, user);
        order = orderRepository.save(order);
        return order;
    }

    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }

    public List<Order> findAllByOwner(String userName){
        User user = userService.findByUserLogin(userName)
                .orElseThrow(()-> new UserNotFoundException("Пользователь с данным логин не найден"));
        return orderRepository.findAllByOwner(user);
    }
}
