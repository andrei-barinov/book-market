package ru.geekbrains.book.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.book.market.dto.BookDto;
import ru.geekbrains.book.market.entities.Book;
import ru.geekbrains.book.market.repositories.AuthorRepository;
import ru.geekbrains.book.market.repositories.BookRepository;
import ru.geekbrains.book.market.services.AuthorService;
import ru.geekbrains.book.market.services.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = BookService.class)
@ActiveProfiles("test")
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testGetBook(){
        Book demoBook = new Book();
        demoBook.setBookTitle("Pinokio");
        demoBook.setBookPrice(234);
        demoBook.setBookId(23L);

        Mockito
                .doReturn(Optional.of(demoBook))
                .when(bookRepository)
                .findById(23L);


        Book b = bookService.findBookById(23L).get();
        Mockito.verify(bookRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(23L));
        Assertions.assertEquals("Pinokio", b.getBookTitle());


        Book demoBookDto = new Book();
        demoBookDto.setBookTitle("Pinokio");
        demoBookDto.setBookPrice(264);
        demoBookDto.setBookId(28L);

        Mockito
                .doReturn(Optional.of(demoBookDto))
                .when(bookRepository)
                .findById(28L);

        BookDto bookDto = bookService.findBookDtoById(28L).get();
        Mockito.verify(bookRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(28L));
        Assertions.assertEquals(264, bookDto.getPrice());

    }

    @Test
    public void testGetListBook(){
        List<Book> listBook = List.of(
                new Book(1L, "Над пропастью во ржи", 135),
                new Book(2L, "Колобок", 189),
                new Book(3L, "Война и мир", 1678)
        );

        Mockito
                .doReturn(listBook)
                .when(bookRepository)
                .findAll();

        List<Book> list = bookService.findAllBook();
        Book a = list.get(0);
        Book b = list.get(1);
        Book c = list.get(2);
        Mockito.verify(bookRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(135, a.getBookPrice());
        Assertions.assertEquals(189, b.getBookPrice());
        Assertions.assertEquals(1678, c.getBookPrice());
        Assertions.assertEquals(3, list.size());
    }

    @Test
    public void testSaveBook(){
        Book testBook = new Book(2L, "Поющие в терновнике", 1500);

        Mockito
                .doReturn(testBook)
                .when(bookRepository)
                .save(testBook);

        Book s = bookService.save(testBook);
        Mockito.verify(bookRepository, Mockito.times(1)).save(testBook);
        Assertions.assertEquals(1500, s.getBookPrice());
    }
}
