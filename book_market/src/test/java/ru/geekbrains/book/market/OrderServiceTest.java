package ru.geekbrains.book.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.book.market.entities.*;
import ru.geekbrains.book.market.repositories.OrderRepository;
import ru.geekbrains.book.market.services.CartService;
import ru.geekbrains.book.market.services.OrderService;
import ru.geekbrains.book.market.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = OrderService.class)
@ActiveProfiles("test")
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private CartService cartService;

    @Test
    public void createFromUserCartTest(){
        User demoUser = new User();
        demoUser.setUserId(1L);
        demoUser.setLogin("misha@mail.com");

        Mockito
                .doReturn(Optional.of(demoUser))
                .when(userService)
                .findByUserLogin("misha@mail.com");

        User user = userService.findByUserLogin("misha@mail.com").get();
        Mockito.verify(userService, Mockito.times(1)).findByUserLogin(ArgumentMatchers.eq("misha@mail.com"));

        Book demoBookDto_1 = new Book(5L, "Поющие в терновнике", 1500);
        CartItem demoCartItem_1 = new CartItem(demoBookDto_1);

        Book demoBookDto_2 = new Book(7L, "Три товарища", 1700);
        CartItem demoCartItem_2 = new CartItem(demoBookDto_2);

        List<CartItem> itemsList = List.of(demoCartItem_1, demoCartItem_2);

        Integer price = itemsList.stream().mapToInt((item) -> item.getPrice()).sum();

        Cart demoCart = new Cart();
        demoCart.setOwnerId(user.getUserId());
        demoCart.setCartItems(itemsList);
        demoCart.setPrice(price);

        Mockito
                .doReturn(demoCart)
                .when(cartService)
                .findCartByOwnerId(user.getUserId());

        Cart cart = cartService.findCartByOwnerId(user.getUserId());
        Mockito.verify(cartService, Mockito.times(1)).findCartByOwnerId(ArgumentMatchers.eq(user.getUserId()));

        Order demoOrder = new Order(cart, "Sverdlova_160", user);

        Mockito
                .doReturn(demoOrder)
                .when(orderRepository)
                .save(demoOrder);

        Order order = orderRepository.save(demoOrder);
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.eq(demoOrder));


        Assertions.assertEquals(1, user.getUserId());
        Assertions.assertEquals(3200, cart.getPrice());
        Assertions.assertEquals("Sverdlova_160", order.getAddress());

    }

    @Test
    public void getOrderTest(){
        User demoUser = new User();
        demoUser.setUserId(3L);
        demoUser.setLogin("dima@mail.com");

        Book demoBookDto_1 = new Book(3L, "Где ночует солнышко", 100);
        Book demoBookDto_2 = new Book(2L, "На западном фронте без перемен", 1100);

        CartItem demoCartItem_1 = new CartItem(demoBookDto_1);
        CartItem demoCartItem_2 = new CartItem(demoBookDto_2);

        List<CartItem> itemsList = List.of(demoCartItem_1, demoCartItem_2);
        List<OrderItem> orderList = List.of(new OrderItem(demoCartItem_1), new OrderItem(demoCartItem_2));

        Integer price = itemsList.stream().mapToInt((item) -> item.getPrice()).sum();

        Cart demoCart = new Cart();
        demoCart.setOwnerId(demoUser.getUserId());
        demoCart.setCartItems(itemsList);
        demoCart.setPrice(price);


        Order demoOrder = new Order(demoCart, "Sverdlova_110", demoUser);
        demoOrder.setOrderId(1L);


        Mockito
                .doReturn(Optional.of(demoOrder))
                .when(orderRepository)
                .findById(1L);

        Order order = orderRepository.findById(1L).get();
        Mockito.verify(orderRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
        Assertions.assertEquals(1200, order.getPrice());
    }

    @Test
    public void findAllByOwnerTest(){
        User demoUser = new User();
        demoUser.setUserId(1L);
        demoUser.setLogin("victor@mail.com");

        Book demoBookDto_1 = new Book(3L, "Где ночует солнышко", 100);
        Book demoBookDto_2 = new Book(2L, "На западном фронте без перемен", 1300);

        CartItem demoCartItem_1 = new CartItem(demoBookDto_1);
        CartItem demoCartItem_2 = new CartItem(demoBookDto_2);

        List<OrderItem> orderItemsList_1 = new ArrayList<>();
        orderItemsList_1.add(new OrderItem(demoCartItem_1));
        orderItemsList_1.add(new OrderItem(demoCartItem_2));

        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setOrderItems(orderItemsList_1);
        order1.setOwner(demoUser);
        order1.setPrice(1400);

        Book demoBookDto_3 = new Book(4L, "Колобок", 100);
        Book demoBookDto_4 = new Book(5L, "Буратино", 900);

        CartItem demoCartItem_3 = new CartItem(demoBookDto_3);
        CartItem demoCartItem_4 = new CartItem(demoBookDto_4);

        List<OrderItem> orderItemsList_2 = new ArrayList<>();
        orderItemsList_2.add(new OrderItem(demoCartItem_3));
        orderItemsList_2.add(new OrderItem(demoCartItem_4));

        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setOrderItems(orderItemsList_2);
        order2.setOwner(demoUser);
        order2.setPrice(1000);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);

        Mockito
                .doReturn(orderList)
                .when(orderRepository)
                .findAllByOwner(demoUser);

        List<Order> list = orderRepository.findAllByOwner(demoUser);
        Mockito.verify(orderRepository, Mockito.times(1)).findAllByOwner(ArgumentMatchers.eq(demoUser));
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(1000, list.get(1).getPrice());
        Assertions.assertEquals(900, list.get(1).getOrderItems().get(1).getPricePerBook());

    }


}
