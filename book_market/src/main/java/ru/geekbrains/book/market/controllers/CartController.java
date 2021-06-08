package ru.geekbrains.book.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.book.market.dto.CartDto;
import ru.geekbrains.book.market.entities.Book;
import ru.geekbrains.book.market.entities.Cart;
import ru.geekbrains.book.market.entities.CartItem;
import ru.geekbrains.book.market.entities.User;
import ru.geekbrains.book.market.services.BookService;
import ru.geekbrains.book.market.services.CartService;
import ru.geekbrains.book.market.services.UserService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final BookService bookService;

    @GetMapping
    public CartDto getCurrentCart(Principal principal) {
        User user = userService.findByUserLogin(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Cart cart = cartService.findCartByOwnerId(user.getUserId());
        log.info("{}", cart);
        return new CartDto(cart);
    }


    @PostMapping
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addProductToCart(Principal principal, @RequestParam(name = "product_id") Long productId) {
        User user = userService.findByUserLogin(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        cartService.addToCart(user.getUserId(), productId);
        return ResponseEntity.ok("");
    }

    @DeleteMapping
    public CartDto clearCart(Principal principal) {
        User user = userService.findByUserLogin(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Cart cart = cartService.clearCart(user.getUserId());
        return new CartDto(cart);
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
