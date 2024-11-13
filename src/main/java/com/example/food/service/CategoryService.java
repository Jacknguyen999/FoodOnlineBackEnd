package com.example.food.service;

import com.example.food.model.Category;

import java.util.List;

public interface CategoryService {
     Category createCategory(String name, Long userId) throws Exception;
     List<Category> findCategoryByRestaurantId(Long id) throws Exception;
    Category findCategoryById(Long id) throws Exception;




}
