package com.example.food.service;

import com.example.food.model.Category;
import com.example.food.model.Restaurent;
import com.example.food.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurent restaurent = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurent(restaurent);


        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {

        /*Restaurent restaurent = restaurantService.getRestaurantByUserId(id);*/
        return categoryRepository.findByRestaurentId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new Exception("Category not found");
        }
        return category.get();
    }
}
