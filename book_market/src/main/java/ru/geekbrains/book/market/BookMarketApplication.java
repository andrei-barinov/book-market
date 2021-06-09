package ru.geekbrains.book.market;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@PropertySource("classpath:private.properties")
public class BookMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMarketApplication.class, args);
	}


}
