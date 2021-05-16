package ru.geekbrains.eureka.client.books.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.eureka.client.books.dto.BookDto;
import ru.geekbrains.eureka.client.books.entities.Book;
import ru.geekbrains.eureka.client.books.exceptions.BookNotFoundException;
import ru.geekbrains.eureka.client.books.services.BookService;
import ru.geekbrains.eureka.client.books.specifications.BookSpecifications;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public BookDto findBookById(@PathVariable Long id){
         return bookService.findBookDtoById(id).orElseThrow(() -> new BookNotFoundException(
                String.format("Не найдена книга с индитификатором %s", id)));
    }

    @PostMapping
    public Book createNewBook(@RequestBody Book book){
        book.setBookId(null);
        return bookService.save(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
         bookService.deleteById(id);
    }

    @GetMapping
    public Page<BookDto> findAllBooks(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        return bookService.findAll(BookSpecifications.build(params), page, 4);
    }

}
