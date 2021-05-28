package ru.geekbrains.book.service.dto;

import lombok.Data;
import ru.geekbrains.book.service.entities.Book;


@Data
public class BookDto {
    private Long id;
    private String title;
    private Integer price;

    public BookDto(Book book) {
        this.id = book.getBookId();
        this.title = book.getBookTitle();
        this.price = book.getBookPrice();
    }
}
