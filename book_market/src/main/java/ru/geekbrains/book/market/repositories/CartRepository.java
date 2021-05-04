package ru.geekbrains.book.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.book.market.entities.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
