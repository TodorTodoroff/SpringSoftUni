package com.example.coffeeshop.services;

import com.example.coffeeshop.model.entites.Category;
import com.example.coffeeshop.model.enums.CategoryTypeEnum;
import com.example.coffeeshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public void seedCategories() {
        if (this.categoryRepository.count() == 0) {

            List<Category> categories = Arrays.stream(CategoryTypeEnum.values())
                    .map(categoryTypeEnum -> {
                                Category category = new Category();
                                category.setName(categoryTypeEnum);
                                switch (categoryTypeEnum) {
                                    case CAKE -> category.setNeededTime(10);
                                    case DRINK -> category.setNeededTime(1);
                                    case COFFEE -> category.setNeededTime(2);
                                    case OTHER -> category.setNeededTime(5);
                                }
                                return category;
                            }
                    ).collect(Collectors.toList());

            this.categoryRepository.saveAll(categories);
        }

    }
}
