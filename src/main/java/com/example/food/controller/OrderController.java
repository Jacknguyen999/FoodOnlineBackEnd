package com.example.food.controller;


import com.example.food.model.CartItem;
import com.example.food.model.Order;
import com.example.food.model.User;
import com.example.food.request.AddCartRequest;
import com.example.food.request.CreateOrderRequest;
import com.example.food.response.PaymentResponse;
import com.example.food.service.OrderService;
import com.example.food.service.PaymentService;
import com.example.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

/*    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(
            @RequestBody CreateOrderRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req,user);
        return ResponseEntity.ok(order);

    }*/

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(
            @RequestBody CreateOrderRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req,user);

        PaymentResponse paymentResponse =paymentService.createPaymentLink(order);
        return ResponseEntity.ok(paymentResponse);

    }
    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        List<Order> order = orderService.getUserOrder(user.getId());
        return ResponseEntity.ok(order);

    }
}
