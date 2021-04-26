package ru.geekbrains.book.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.book.market.dto.BookDto;
import ru.geekbrains.book.market.entities.Book;
import ru.geekbrains.book.market.exception.BookNotFoundException;
import ru.geekbrains.book.market.services.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDto> findAllBook(){
        return bookService.findAll();
    }

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

}
