package ru.geekbrains.rest.client.books.front.controllers;

import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.rest.client.books.front.dto.BookDto;
import ru.geekbrains.rest.client.books.front.entities.Book;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class MainController {
    private final RestTemplate restTemplate;

    @GetMapping
    public String getAllBooks(@RequestParam(name = "p", defaultValue = "1") Integer page){
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("p", page);
        String data = restTemplate.getForObject("http://localhost:8191/api/v1/books", String.class, params);
        return data;
    }

    @GetMapping("/{id}")
    public BookDto findBookById(@PathVariable Long id){
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        BookDto data = restTemplate.getForObject("http://localhost:8191/api/v1/books/{id}", BookDto.class, params);
        return data;
    }

    @PostMapping
    public Book createNewBook(@RequestBody Book book){
        Book data = restTemplate.postForObject("http://localhost:8191/api/v1/books", book, Book.class);
        return data;
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        restTemplate.getForObject("http://localhost:8191/api/v1/books", Book.class, params);
    }


}
