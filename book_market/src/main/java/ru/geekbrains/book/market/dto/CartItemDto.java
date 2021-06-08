package ru.geekbrains.book.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.book.market.entities.CartItem;

@Data
@NoArgsConstructor
public class CartItemDto {
    private Long bookId;
    private String bookTitle;
    private int quantity;
    private int pricePerBook;
    private int price;

    public CartItemDto(CartItem cartItem) {
        this.bookId = cartItem.getBookId();
        this.bookTitle = cartItem.getTitle();
        this.quantity = cartItem.getQuantity();
        this.pricePerBook = cartItem.getPricePerBook();
        this.price = cartItem.getPrice();
    }
}
