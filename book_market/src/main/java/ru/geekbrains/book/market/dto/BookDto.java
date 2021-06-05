package ru.geekbrains.book.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.book.market.entities.Book;

@Data
@NoArgsConstructor
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
