package com.example.food.controller;


import com.example.food.DTO.RestaurentDTO;
import com.example.food.model.Restaurent;
import com.example.food.model.User;
import com.example.food.request.CreateRestaurantRequest;
import com.example.food.service.RestaurantService;
import com.example.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<Restaurent>> searchRestaurent(
             @RequestHeader("Authorization") String jwt,
             @RequestParam String keyword) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        List<Restaurent> restaurent = restaurantService.searchRestaurant(keyword);

        return new ResponseEntity<>(restaurent, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<Restaurent>> getAllRestaurent(
            @RequestHeader("Authorization") String jwt
            ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        List<Restaurent> restaurent = restaurantService.getAllRestaurants();

        return new ResponseEntity<>(restaurent, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurent> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable long id
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Restaurent restaurent = restaurantService.findRestaurantById(id);

        return new ResponseEntity<>(restaurent, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurentDTO> addtoFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable long id
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        RestaurentDTO restaurant = restaurantService.addtoFavorite(id,user);



        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }

}
