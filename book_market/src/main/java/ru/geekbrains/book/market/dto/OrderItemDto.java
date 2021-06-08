package ru.geekbrains.book.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.book.market.entities.OrderItem;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long bookId;
    private String bookTitle;
    private Integer pricePerBook;
    private Integer price;
    private Integer quantity;

    public OrderItemDto(OrderItem orderItem) {
        this.bookId = orderItem.getBookId();
        this.bookTitle = orderItem.getTitle();
        this.pricePerBook = orderItem.getPricePerBook();
        this.price = orderItem.getTotalPrice();
        this.quantity = orderItem.getQuantity();
    }
}
