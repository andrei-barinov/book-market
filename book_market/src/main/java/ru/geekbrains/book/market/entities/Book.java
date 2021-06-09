package ru.geekbrains.book.market.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long bookId;

    @Column(name = "title")
    private String bookTitle;

    @Column(name = "price")
    private int bookPrice;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime bookCreatedAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime bookUpdatedAt;

    @ManyToMany
    @JoinTable(name = "author_book",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Collection<Author> bookAuthors;

    @ManyToMany
    @JoinTable(name = "category_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Collection<Category> bookCategories;

    public Book(Long id, String title, Integer price){
        bookId = id;
        bookTitle = title;
        bookPrice = price;
    }

}
