package ru.geekbrains.book.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.book.market.entities.Order;
import ru.geekbrains.book.market.entities.OrderItem;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private String username;
    private String address;
    private Integer totalPrice;
    private List<OrderItemDto> items;
    private String creationDateTime;

    public OrderDto(Order order) {
        this.id = order.getOrderId();
        this.username = order.getOwner().getLogin();
        this.address = order.getAddress();
        this.totalPrice = order.getPrice();
        this.creationDateTime = order.getCreatedAt().toString();
        this.items = new ArrayList<>();
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.forEach(i -> this.items.add(new OrderItemDto(i)));
    }
}
