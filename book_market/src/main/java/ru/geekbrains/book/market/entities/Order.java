package ru.geekbrains.book.market.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long oderId;

    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private Integer price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Order(Cart cart, String address, User owner) {
        this.owner = owner;
        this.address = address;
        this.price = cart.getPrice();
        this.orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem(cartItem);
            orderItem.setOrder(this);
            this.orderItems.add(orderItem);
        }
    }
}
