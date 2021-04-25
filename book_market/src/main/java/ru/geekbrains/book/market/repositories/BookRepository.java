package ru.geekbrains.book.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.book.market.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
