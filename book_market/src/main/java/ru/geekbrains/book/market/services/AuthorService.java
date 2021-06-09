package ru.geekbrains.book.market.services;

import org.springframework.stereotype.Service;
import ru.geekbrains.book.market.entities.Author;
import ru.geekbrains.book.market.soap.authors.AuthorXSD;

import java.util.function.Function;

@Service
public class AuthorService {
    public static final Function<Author, AuthorXSD> functionEntityToSoap = author -> {
        AuthorXSD authorXSD = new AuthorXSD();
        authorXSD.setId(author.getAuthorId());
        authorXSD.setFirstName(author.getAuthorFirstName());
        authorXSD.setLastName(author.getAuthorLastName());
        return authorXSD;
    };
}
