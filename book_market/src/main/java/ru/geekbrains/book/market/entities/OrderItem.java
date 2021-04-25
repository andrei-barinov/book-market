package ru.geekbrains.book.market.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders_items")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderItemsId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "title")
    private String title;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="price_per_product")
    private Integer pricePerProduct;

    @Column(name="total_price")
    private Integer totalPrice;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime bookCreatedAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime bookUpdatedAt;
}
