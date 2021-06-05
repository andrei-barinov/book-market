package ru.geekbrains.book.market;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.book.market.services.OrderService;

@SpringBootTest(classes = OrderService.class)
@ActiveProfiles("test")
public class OrderServiceTest {

}
