package com.example.coffeeshop.model.dto;

import com.example.coffeeshop.model.entites.Category;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddOfferDTO {


    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotNull
    @PastOrPresent
    private LocalDateTime orderTime;

    @NotNull
    private Category category;

    @NotEmpty
    @Size(min = 5)
    private String description;


    public AddOfferDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
