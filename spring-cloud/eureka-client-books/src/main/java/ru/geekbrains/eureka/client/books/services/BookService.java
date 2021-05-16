package ru.geekbrains.eureka.client.books.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.eureka.client.books.dto.BookDto;
import ru.geekbrains.eureka.client.books.entities.Book;
import ru.geekbrains.eureka.client.books.repositories.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }

    public Optional<BookDto> findBookDtoById(Long id){
        return bookRepository.findById(id).map(BookDto::new);
    }

    public List<BookDto> findAll(){
        return bookRepository.findAll().stream().map(BookDto::new).collect(Collectors.toList());
    }

    public List<BookDto> findAllBook(){
        return bookRepository.findAll().stream().map(BookDto::new).collect(Collectors.toList());
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public Page<BookDto> findAll(Specification<Book> spec, int page, int pageSize) {
        return bookRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(BookDto::new);

    }

}
