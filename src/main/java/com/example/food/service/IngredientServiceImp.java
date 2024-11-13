package com.example.food.service;


import com.example.food.model.IngredientsCategory;
import com.example.food.model.IngredientsItem;
import com.example.food.model.Restaurent;
import com.example.food.repository.IngredientCategoryRepository;
import com.example.food.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImp implements IngredientService {
    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception {
        Restaurent restaurent = restaurantService.findRestaurantById(restaurantId);

        IngredientsCategory category = new IngredientsCategory();
        category.setName(name);
        category.setRestaurant(restaurent);

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception {
        Optional<IngredientsCategory> opt = ingredientCategoryRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(long id) throws Exception {
       restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurent restaurent = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory category = findIngredientsCategoryById(categoryId);

        IngredientsItem item = new IngredientsItem();
        item.setName(ingredientName);
        item.setRestaurent(restaurent);
        item.setCategory(category);
        IngredientsItem item1 = ingredientItemRepository.save(item);
        category.getIngredients().add(item1);
        return item1;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {

        return ingredientItemRepository.findByRestaurentId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(long id) throws Exception {

        Optional<IngredientsItem> opt = ingredientItemRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Ingredient item not found");
        }
        IngredientsItem ingredientsItem = opt.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientItemRepository.save(ingredientsItem);
    }
}
