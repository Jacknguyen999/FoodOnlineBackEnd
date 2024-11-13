package com.example.food.repository;

import com.example.food.model.IngredientsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IngredientCategoryRepository extends JpaRepository<IngredientsCategory, Long> {
    List<IngredientsCategory> findByRestaurantId(Long id);

}
