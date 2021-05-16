package ru.geekbrains.rest.client.books.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.rest.client.books.front.entities.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
