package ru.geekbrains.book.market.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@NoArgsConstructor
@RedisHash("cart_items")
public class CartItem {
    @Id
    private Long id;

    @Indexed
    private Cart cart;

    private Long bookId;

    private String title;

    private Integer quantity;

    private Integer pricePerBook;

    private Integer price;

    public CartItem(Book book) {
        this.bookId= book.getBookId();
        this.title = book.getBookTitle();
        this.quantity = 1;
        this.pricePerBook = book.getBookPrice();
        this.price = pricePerBook;
    }
}
