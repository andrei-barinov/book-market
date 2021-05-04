package ru.geekbrains.book.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@NoArgsConstructor
@RedisHash("cart_item")
public class CartItem {
    @Id
    private Long id;

    @Indexed
    private Cart cart;

    private Book book;

    private String title;

    private Integer quantity;

    private Integer pricePerProduct;

    private Integer price;

    public CartItem(Book book) {
        this.book = book;
        this.title = book.getBookTitle();
        this.quantity = 1;
        this.pricePerProduct = book.getBookPrice();
        this.price = pricePerProduct;
    }
}
