package ru.geekbrains.book.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.book.market.entities.Book;
import ru.geekbrains.book.market.entities.CartItem;
import ru.geekbrains.book.market.repositories.CartItemRepository;
import ru.geekbrains.book.market.services.BookService;
import ru.geekbrains.book.market.services.CartItemService;

import java.util.Optional;

@SpringBootTest(classes = CartItemService.class)
@ActiveProfiles("test")
public class CartItemTest {

    @Autowired
    private CartItemService cartItemService;

    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetCartItem(){
        Book demoBookDto = new Book(5L, "Поющие в терновнике", 1500);
        CartItem demoCartItem = new CartItem(demoBookDto);

        Mockito
                .doReturn(Optional.of(demoCartItem))
                .when(cartItemRepository)
                .findById(5L);

        CartItem CI = cartItemService.findById(5L).get();
        Mockito.verify(cartItemRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(5L));
        Assertions.assertEquals("Поющие в терновнике", CI.getTitle());

    }

    @Test
    public void testSaveCartItem(){
        Book demoBookDto = new Book(7L, "Сказки Пушкина", 1300);
        CartItem demoCartItem = new CartItem(demoBookDto);

        Mockito
                .doReturn(demoCartItem)
                .when(cartItemRepository)
                .save(demoCartItem);

        CartItem CI = cartItemRepository.save(demoCartItem);
        Mockito.verify(cartItemRepository, Mockito.times(1)).save(demoCartItem);
        Assertions.assertEquals(1300, CI.getPrice());
    }


}
