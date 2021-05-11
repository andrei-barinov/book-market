package ru.geekbrains.book.market.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.book.market.entities.Cart;
import ru.geekbrains.book.market.entities.CartItem;
import ru.geekbrains.book.market.repositories.CartRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;

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
}
