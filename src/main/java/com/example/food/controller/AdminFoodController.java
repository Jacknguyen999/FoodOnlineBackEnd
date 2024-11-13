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

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity<Food> createFood(
            @RequestBody CreateFoodRequest req,

            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurent restaurent = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req,req.getCategory(),restaurent);
        return new ResponseEntity<>(food, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);

        MessageResponse res = new MessageResponse();
        res.setMesssage("Food deleted");




        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvaibilityStatus(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailibilityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.CREATED);

    }




}
