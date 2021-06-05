package ru.geekbrains.book.market;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.geekbrains.book.market.entities.Book;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JSONTester {

    @Autowired
    private JacksonTester<Book> jackson;

    @Test
    public void jsonSerializationTest() throws Exception{
        Book book = new Book(2L, "Поющие в терновнике", 1500);


        assertThat(jackson.write(book))
                .hasJsonPathNumberValue("$.bookId")
                .extractingJsonPathNumberValue("$.bookId").isEqualTo(2);

        assertThat(jackson.write(book))
                .hasJsonPathNumberValue("$.bookId")
                .extractingJsonPathStringValue("$.bookTitle").isEqualTo("Поющие в терновнике");

        assertThat(jackson.write(book))
                .hasJsonPathNumberValue("$.bookId")
                .extractingJsonPathNumberValue("$.bookPrice").isEqualTo(1500);
    }

    @Test
    public void jsonDeserializationTest() throws Exception{

        String content = "{\"bookId\":3,\"bookTitle\":\"Поющие в терновнике\",\"bookPrice\":1200}";

        Book book = new Book(3L, "Поющие в терновнике", 1200);

        assertThat(jackson.parse(content)).isEqualTo(book);
        assertThat(jackson.parseObject(content).getBookPrice()).isEqualTo(1200);
    }
}
