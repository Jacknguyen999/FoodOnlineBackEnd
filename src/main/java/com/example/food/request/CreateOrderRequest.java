package com.example.food.request;


import com.example.food.model.Address;
import lombok.Data;

@Data
public class CreateOrderRequest {

    private Long restaurantId;
    private Address deliveryAddress;


}
