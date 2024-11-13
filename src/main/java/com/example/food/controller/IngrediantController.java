package com.example.food.controller;


import com.example.food.model.IngredientsCategory;
import com.example.food.model.IngredientsItem;
import com.example.food.request.IngredientCategoryRequest;
import com.example.food.request.IngredientItemRequest;
import com.example.food.service.IngredientService;
import com.example.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngrediantController {
    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private UserService userService;

    @PostMapping("/category")
    public ResponseEntity<IngredientsCategory> createIngredientsCategory(
            @RequestBody IngredientCategoryRequest req
            ) throws Exception {
        IngredientsCategory item = ingredientService.createIngredientsCategory(req.getName(),req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<IngredientsItem> createIngredientsItem(
            @RequestBody IngredientItemRequest req
    ) throws Exception {
        IngredientsItem item = ingredientService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientsStock(
            @PathVariable Long id
    ) throws Exception {
        IngredientsItem item = ingredientService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredients(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientsItem> items = ingredientService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientsCategory>> getRestaurantIngredientsCategory(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientsCategory> items =  ingredientService.findIngredientsCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }





}
