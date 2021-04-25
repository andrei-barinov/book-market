package ru.geekbrains.book.market.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.book.market.entities.Order;
import ru.geekbrains.book.market.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserService userService;
    private final OrderRepository orderRepository;

    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }

    public List<Order> findAllByOwner(String userName){
        return orderRepository.findAllByOwner(userName);
    }
}
