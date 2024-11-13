package com.example.food.service;

import com.example.food.model.IngredientsCategory;
import com.example.food.model.IngredientsItem;

import java.util.List;

public interface IngredientService  {

     IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception;

    IngredientsCategory findIngredientsCategoryById(Long id) throws Exception;

    List<IngredientsCategory>  findIngredientsCategoryByRestaurantId(long id) throws Exception;

    IngredientsItem createIngredientItem(Long restaurantId,String ingredientName, Long categoryId) throws Exception;

    List<IngredientsItem> findRestaurantsIngredients(Long restaurantId);

    IngredientsItem updateStock(long id) throws Exception;




}


