package ru.geekbrains.book.market.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;
import ru.geekbrains.book.market.entities.Book;
import ru.geekbrains.book.market.entities.OrderItem;
import ru.geekbrains.book.market.exception.BookNotFoundException;
import ru.geekbrains.book.market.services.BookService;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Data
@RedisHash("carts")
public class Cart {
    @Id
    private Long ownerId;

    private List<CartItem> cartItems;

    private Integer price;

    public Cart(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

}
