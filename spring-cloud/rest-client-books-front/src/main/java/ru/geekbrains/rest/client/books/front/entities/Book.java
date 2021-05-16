package ru.geekbrains.rest.client.books.front.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
public class Book {

    private Long bookId;

    private String bookTitle;

    private int bookPrice;

    private LocalDateTime bookCreatedAt;

    private LocalDateTime bookUpdatedAt;

    private Collection<Author> bookAuthors;

    private Collection<Category> bookCategories;

}
