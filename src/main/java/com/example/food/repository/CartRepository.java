package com.example.food.repository;

import com.example.food.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {


    public Cart findByCustomerId(Long userId);



}
