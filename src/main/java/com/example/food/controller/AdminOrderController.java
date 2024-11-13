package com.example.food.controller;

import com.example.food.model.Order;
import com.example.food.model.User;
import com.example.food.request.CreateOrderRequest;
import com.example.food.service.OrderService;
import com.example.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        List<Order> order = orderService.getRestaurantsOrder(id,order_status);
        return ResponseEntity.ok(order);

    }
    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @PathVariable String orderStatus,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Order order = orderService.updateOrder(id,orderStatus);
        return ResponseEntity.ok(order);

    }
}
