package com.example.food.service;

import com.example.food.DTO.RestaurentDTO;
import com.example.food.model.Restaurent;
import com.example.food.model.User;
import com.example.food.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService  {

    public Restaurent createRestaurent(CreateRestaurantRequest req, User user);

    public Restaurent updateRestaurent(Long restaurentId, CreateRestaurantRequest updatedRestaurant) throws Exception;

    public void deleteRestaurent(Long restaurentId) throws Exception;


    public List<Restaurent> getAllRestaurants();

    public List<Restaurent> searchRestaurant(String keyword);

    public Restaurent findRestaurantById(Long id) throws Exception;

    public Restaurent getRestaurantByUserId(long userId) throws Exception;

    public RestaurentDTO addtoFavorite(Long restaurentId, User user) throws Exception;

    public Restaurent updateRestaurentStatus(Long id) throws Exception;


}


