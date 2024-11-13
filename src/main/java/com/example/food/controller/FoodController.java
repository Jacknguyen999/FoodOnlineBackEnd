package com.example.food.controller;


import com.example.food.model.Food;
import com.example.food.model.Restaurent;
import com.example.food.model.User;
import com.example.food.request.CreateFoodRequest;
import com.example.food.response.MessageResponse;
import com.example.food.service.FoodService;
import com.example.food.service.RestaurantService;
import com.example.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(
            @RequestParam String name,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> food = foodService.searchFood(name);

        return new ResponseEntity<>(food, HttpStatus.OK);

    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam (required = false) boolean vegetarian,
            @RequestParam (required = false) boolean seasonal,
            @RequestParam (required = false) boolean nonveq,
            @RequestParam(required = false) String food_category,
            @PathVariable long restaurantId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> food = foodService.getRestaurantFood(restaurantId, vegetarian, nonveq, seasonal, food_category);

        return new ResponseEntity<>(food, HttpStatus.OK);

    }


}
