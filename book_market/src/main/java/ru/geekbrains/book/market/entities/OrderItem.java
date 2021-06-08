package ru.geekbrains.book.market.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderItemsId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

//    @ManyToOne
//    @JoinColumn(name = "book_id")
    private Long bookId;

    @Column(name = "title")
    private String title;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="price_per_book")
    private Integer pricePerBook;

    @Column(name="total_price")
    private Integer totalPrice;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime bookCreatedAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime bookUpdatedAt;

    public OrderItem(CartItem cartItem) {
        this.bookId = cartItem.getBookId();
        this.title = cartItem.getTitle();
        this.quantity = cartItem.getQuantity();
        this.pricePerBook = cartItem.getPricePerBook();
        this.totalPrice = cartItem.getPrice();
    }
}
