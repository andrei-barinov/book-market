package ru.geekbrains.book.market.services;

import org.springframework.stereotype.Service;
import ru.geekbrains.book.market.entities.Category;
import ru.geekbrains.book.market.soap.categories.CategoryXSD;

import java.util.function.Function;

@Service
public class CategoryService {
    public static final Function<Category, CategoryXSD> functionEntityToSoap = category -> {
        CategoryXSD categoryXSD = new CategoryXSD();
        categoryXSD.setId(category.getCategoryId());
        categoryXSD.setName(category.getCategoryName());
        return categoryXSD;
    };
}
