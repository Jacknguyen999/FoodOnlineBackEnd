package com.example.food.request;


import lombok.Data;

@Data
public class UpdateCartItemReqeust {

    private Long cartItemId;
    private int quantity;

}
