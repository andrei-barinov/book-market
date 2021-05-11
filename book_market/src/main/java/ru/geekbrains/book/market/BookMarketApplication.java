package ru.geekbrains.book.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:private.properties")
public class BookMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMarketApplication.class, args);
	}

}
