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
@RedisHash("cart")
public class Cart {
    //    @Id
//    private Long id;
    @Id
    private Long ownerId;

    //    @Indexed
    private List<CartItem> cartItems;

    private Integer price;

    public Cart(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Cart() {
        this.cartItems = new ArrayList<>();
    }
    //    public void add(CartItem cartItem) {
//        for (CartItem item : cartItems) {
//            if (item.getProduct().getId().equals(cartItem.getProduct().getId())) {
//                item.increment(cartItem.getQuantity());
//                calculate();
//                return;
//            }
//        }
//        cartItems.add(cartItem);
//        cartItem.setCart(this);
//        calculate();
//    }
//
//    public void calculate() {
//        price = new BigDecimal(0.0);
//        for (CartItem cartItem : cartItems) {
//            price.add(cartItem.getPrice());
//        }
//    }

}
