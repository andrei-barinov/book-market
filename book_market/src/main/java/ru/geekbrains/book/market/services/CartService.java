package ru.geekbrains.book.market.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.book.market.entities.Cart;
import ru.geekbrains.book.market.entities.CartItem;
import ru.geekbrains.book.market.exception.BookNotFoundException;
import ru.geekbrains.book.market.repositories.CartRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class CartService {
    private CartRepository cartRepository;
    private BookService bookService;

    public Cart updateCart(Cart cart) {
        recalculateCart(cart);
        return cartRepository.save(cart);
    }

    public Cart findCartByOwnerId(Long id) {
        Cart cart = cartRepository.findById(id).orElse(new Cart(id));
        return cart;
    }

    public Cart clearCart(Long id) {
        Cart cart = findCartByOwnerId(id);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }

    private void recalculateCart(Cart cart) {
        cart.setPrice(0);
        for (CartItem cartItem : cart.getCartItems()) {
            cart.setPrice(cart.getPrice() + (cartItem.getPrice()));
        }
    }

    public void addToCart(Long userId, Long productId) {
        log.debug("Adding to cart");
        Cart cart = findCartByOwnerId(userId);
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getBookId().equals(productId)) {
                log.debug("Found existing item {}", cartItem.getTitle());
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                recalculateCart(cart);
                cartRepository.save(cart);
                log.debug("Add item to cart {}:{}", cartItem.getTitle(), cartItem.getQuantity());
                return;
            }
        }
        CartItem item = new CartItem(bookService.findBookById(productId).orElseThrow(() -> new BookNotFoundException("Not found!")));
        cart.getCartItems().add(item);
        recalculateCart(cart);
        cartRepository.save(cart);
    }
}
