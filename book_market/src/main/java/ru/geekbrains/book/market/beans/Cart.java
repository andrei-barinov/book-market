package ru.geekbrains.book.market.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.book.market.entities.Book;
import ru.geekbrains.book.market.entities.OrderItem;
import ru.geekbrains.book.market.exception.BookNotFoundException;
import ru.geekbrains.book.market.services.BookService;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private List<OrderItem> items;

    private final BookService bookService;

    private int totalPrice;

    @PostConstruct
    public void init(){
        this.items = new ArrayList<>();
    }

    public void addToCart(Long id){
        for(OrderItem o: items){
            if(o.getBook().getBookId().equals(id)){
                o.incrementQuantity();
                return;
            }
        }
        Book b =bookService.findBookById(id).orElseThrow(() -> new BookNotFoundException(
                "Не найден товар с индитификатором " + id + "(addToCart)"));
        OrderItem orderItem = new OrderItem(b);
        items.add(orderItem);
        recalculate();
    }

    public void clear(){
        items.clear();
        recalculate();
    }

    public void recalculate(){
        totalPrice = 0;
        for(OrderItem o: items){
            totalPrice += o.getTotalPrice();
        }
    }

}
