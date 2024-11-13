package com.example.food.service;

import com.example.food.model.Category;
import com.example.food.model.Food;
import com.example.food.model.Restaurent;
import com.example.food.repository.FoodRepository;
import com.example.food.repository.RestaurantRepository;
import com.example.food.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImp implements FoodService {
    @Autowired
    private FoodRepository foodRepository;


    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurent restaurent) {

        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurent(restaurent);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasional());
        food.setVegetarian(req.isVegetarian());
        food.setCreationDate(new Date());

        Food savedFood = foodRepository.save(food);
        restaurent.getFoods().add(savedFood);


        return savedFood;
    }

    @Override
    public void deleteFood(Long foodid) throws Exception {

        Food food = FindFoodByID(foodid);
        food.setRestaurent(null);
        foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantid,
                                        boolean isVegitarian, boolean isNonveg,
                                        boolean isSeasonal, String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurentId(restaurantid);
        
        if(isVegitarian){
            foods = filterByVegetarian(foods,isVegitarian);
        }
        if(isSeasonal){
            foods = filterBySeaSonal(foods,isSeasonal);
        }
        if(isNonveg){
            foods = filterByNonveg(foods,isNonveg);
        }
        if(foodCategory !=null && !foodCategory.equals("")){
            foods = filterByCategory(foods, foodCategory);
        }
        return  foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food ->
        {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());



    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food ->food.isVegetarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterBySeaSonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food ->food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegitarian) {
        return foods.stream().filter(food ->food.isVegetarian()==isVegitarian).collect(Collectors.toList());
        
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food FindFoodByID(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if(optionalFood.isEmpty()){
            throw new Exception("Not found food");
        }

        return optionalFood.get();
    }

    @Override
    public Food updateAvailibilityStatus(Long foodId) throws Exception {
        Food food = FindFoodByID(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
