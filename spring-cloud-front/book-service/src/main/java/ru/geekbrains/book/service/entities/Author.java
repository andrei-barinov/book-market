package ru.geekbrains.book.service.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "author")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long authorId;

    @Column(name = "first_name")
    private String authorFirstName;

    @Column(name = "last_name")
    private String authorLastName;
}
