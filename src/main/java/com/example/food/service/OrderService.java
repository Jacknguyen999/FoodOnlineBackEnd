package com.example.food.service;


import com.example.food.model.Order;
import com.example.food.model.User;
import com.example.food.request.CreateOrderRequest;

import java.util.List;

public interface OrderService {

    public Order createOrder(CreateOrderRequest order, User user) throws Exception;

    Order updateOrder(Long orderId,String orderStatus) throws Exception;

    void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUserOrder(Long userId) throws Exception;

    List<Order> getRestaurantsOrder(Long restaurantId,String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;
}
