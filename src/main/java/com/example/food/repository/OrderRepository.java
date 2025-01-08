package com.example.food.repository;

import com.example.food.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface OrderRepository  extends JpaRepository<Order, Long> {


    /*@Query("SELECT f FROM Order f " +
            "WHERE f.customer.id = 6")*/
    List<Order> findByCustomerId(Long userId);

    List<Order> findByRestaurentId(Long restaurantId) ;




}
