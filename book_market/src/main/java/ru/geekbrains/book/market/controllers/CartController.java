package ru.geekbrains.book.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.book.market.entities.Book;
import ru.geekbrains.book.market.entities.Cart;
import ru.geekbrains.book.market.entities.CartItem;
import ru.geekbrains.book.market.entities.User;
import ru.geekbrains.book.market.services.BookService;
import ru.geekbrains.book.market.services.CartService;
import ru.geekbrains.book.market.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final BookService bookService;

    @GetMapping
    public Cart getCurrentCart(Principal principal) {
//        if (principal == null) {
//            return cartService.getCartForUser(null, null);
//        }
        User user = userService.findByUserLogin(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return cartService.findCartByOwnerId(user.getUserId());
    }

    @PostMapping
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }

    @DeleteMapping
    public Cart clearCart(Principal principal) {
        User user = userService.findByUserLogin(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return cartService.clearCart(user.getUserId());
    }

    @GetMapping("/mock")
    public Cart getMockCart(Principal principal) {
//        if (principal == null) {
//            return cartService.getCartForUser(null, null);
//        }
        User user = userService.findByUserLogin(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Cart cart = new Cart();
        List<CartItem> items = new ArrayList<>();
        List<Book> books = bookService.findAllBook();
        for (Book book : books) {
            items.add(new CartItem(book));
        }
        cart.setOwnerId(1L);
        cart.setCartItems(items);
        return cart;
    }
}
