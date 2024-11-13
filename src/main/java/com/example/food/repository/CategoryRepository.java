package com.example.food.repository;

import com.example.food.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByRestaurentId(Long restaurantId);
}
