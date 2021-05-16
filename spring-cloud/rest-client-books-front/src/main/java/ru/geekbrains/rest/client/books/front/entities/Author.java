package ru.geekbrains.rest.client.books.front.entities;

import lombok.Data;

@Data
public class Author {

    private Long authorId;
    private String authorFirstName;
    private String authorLastName;

}
