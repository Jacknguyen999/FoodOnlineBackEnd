package com.example.food.request;


import com.example.food.model.Category;
import com.example.food.model.IngredientsItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private long price;
    private Category category;
    private List<String> images;
    private LocalDateTime date;
    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasional;
    private List<IngredientsItem> ingredients;

}
