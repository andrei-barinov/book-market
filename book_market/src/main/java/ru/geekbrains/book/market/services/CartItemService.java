package ru.geekbrains.book.market.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.book.market.entities.Cart;
import ru.geekbrains.book.market.entities.CartItem;
import ru.geekbrains.book.market.exception.BookNotFoundException;
import ru.geekbrains.book.market.repositories.CartItemRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final BookService bookService;

    public void addToCart(Cart cart, Long bookId) {
        CartItem cartItem = new CartItem(bookService.findBookById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        String.format("Не найдена книга с id %s", bookId))));
        cartItemRepository.save(cartItem);
    }

    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    public CartItem saveOrUpdate(CartItem item) {
        return cartItemRepository.save(item);
    }
}
