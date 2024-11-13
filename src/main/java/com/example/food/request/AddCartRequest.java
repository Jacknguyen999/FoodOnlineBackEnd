package com.example.food.request;


import lombok.Data;

import java.util.List;

@Data
public class AddCartRequest {

    private Long foodId;

    private int quantity;

    private List<String> ingredients;
}
