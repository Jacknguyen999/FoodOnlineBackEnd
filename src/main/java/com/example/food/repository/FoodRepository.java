package com.example.food.repository;

import com.example.food.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByRestaurentId(Long restaurantId);

    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% or f.foodCategory.name LIKE %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);

    /*@Modifying
    @Transactional
    @Query("DELETE FROM Food f WHERE f.restaurent.id = :restaurentId")
    void deleteFoodsByRestaurantId(@Param("restaurentId") Long restaurentId);*/




}
