package ru.geekbrains.book.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.book.market.dto.BookDto;
import ru.geekbrains.book.market.entities.Book;
import ru.geekbrains.book.market.exception.BookNotFoundException;
import ru.geekbrains.book.market.services.BookService;
import ru.geekbrains.book.market.specifications.BookSpecifications;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;

//    @GetMapping
//    public List<BookDto> findAllBook(){
//        return bookService.findAll();
//    }

    @GetMapping("/{id}")
    public BookDto findBookById(@PathVariable Long id){
         return bookService.findBookDtoById(id).orElseThrow(() -> new BookNotFoundException(
                String.format("Не найдена книга с индитификатором %s", id)));
    }

//    @GetMapping("/{id}")
//    public Book findBookById(@PathVariable Long id){
//        return bookService.findBookById(id).orElseThrow(() -> new BookNotFoundException(
//                String.format("Не найдена книга с индитификатором %s", id)));
//    }

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
