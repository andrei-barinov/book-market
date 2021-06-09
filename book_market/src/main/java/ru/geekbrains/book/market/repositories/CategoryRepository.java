package ru.geekbrains.book.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.book.market.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
