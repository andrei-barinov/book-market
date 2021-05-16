package ru.geekbrains.eureka.client.books.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.eureka.client.books.entities.Book;

public class BookSpecifications {
    private static Specification<Book> priceGreaterOrEqualsThan(int minPrice) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private static Specification<Book> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    private static Specification<Book> titleLike(String titlePart) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

    public static Specification<Book> build(MultiValueMap<String, String> params) {
        Specification<Book> spec = Specification.where(null);
        if (params.containsKey("min_price") && !params.getFirst("min_price").isBlank()) {
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.getFirst("min_price"))));
        }
        if (params.containsKey("max_price") && !params.getFirst("max_price").isBlank()) {
            spec = spec.and(BookSpecifications.priceLesserOrEqualsThan(Integer.parseInt(params.getFirst("max_price"))));
        }
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and(BookSpecifications.titleLike(params.getFirst("title")));
        }
        return spec;
    }
}
