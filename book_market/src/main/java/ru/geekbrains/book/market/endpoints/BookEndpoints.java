package ru.geekbrains.book.market.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.book.market.services.BookService;
import ru.geekbrains.book.market.soap.books.GetAllBooksRequest;
import ru.geekbrains.book.market.soap.books.GetAllBooksResponse;

import javax.transaction.Transactional;

@Endpoint
@RequiredArgsConstructor
public class BookEndpoints {
    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/spring/ws/books";
    private final BookService bookService;

    @Transactional
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBooksRequest")
    @ResponsePayload
    public GetAllBooksResponse getAllBooks(@RequestPayload GetAllBooksRequest request) {
        GetAllBooksResponse response = new GetAllBooksResponse();
        bookService.getAllBooks().forEach(response.getBooks()::add);
        return response;
    }
}
