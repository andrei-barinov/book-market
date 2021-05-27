package ru.geekbrains.book.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.book.service.dto.BookDto;
import ru.geekbrains.book.service.entities.Book;
import ru.geekbrains.book.service.repositories.BookRepository;

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

    public List<Book> findAllBook(){
        return bookRepository.findAll();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public Page<BookDto> findAll(int page) {
        return bookRepository.findAll(PageRequest.of(page-1, 5)).map(BookDto::new);
    }


}
