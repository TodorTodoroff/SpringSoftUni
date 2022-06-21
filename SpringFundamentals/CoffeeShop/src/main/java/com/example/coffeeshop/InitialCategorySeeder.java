package com.example.coffeeshop;

import com.example.coffeeshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialCategorySeeder implements CommandLineRunner {


    private final CategoryService categoryService;

    @Autowired
    public InitialCategorySeeder(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.categoryService.seedCategories();

    }

}
