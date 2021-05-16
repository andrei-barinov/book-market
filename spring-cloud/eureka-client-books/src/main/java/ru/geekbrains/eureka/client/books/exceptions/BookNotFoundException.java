package ru.geekbrains.eureka.client.books.exceptions;

public class BookNotFoundException extends NotFoundException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
