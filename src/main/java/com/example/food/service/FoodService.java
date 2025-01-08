package com.example.food.service;

import com.example.food.model.Category;
import com.example.food.model.Food;
import com.example.food.model.Restaurent;
import com.example.food.request.CreateFoodRequest;

import java.io.IOException;
import java.util.List;

public interface FoodService {

     Food createFood(CreateFoodRequest req, Category category, Restaurent restaurent);


     void deleteFood(Long foodid) throws Exception;

     List<Food> getRestaurantFood(Long restaurantid, boolean isVegitarian ,
                                  boolean isNonveg, boolean isSeasonal,
                                  String foodCategory);
     List<Food> searchFood(String keyword);
     Food FindFoodByID(Long foodId) throws Exception;

     Food updateAvailibilityStatus(Long foodId) throws Exception;

     List<Food> getALlFood();





}
