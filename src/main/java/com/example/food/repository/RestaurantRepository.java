package com.example.food.repository;

import com.example.food.model.Restaurent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurent,Long> {

    @Query("SELECT r FROM Restaurent  r where lower(r.cuisineType) LIKE lower(concat('%',:query,'%')) " +
            "OR lower(r.name) LIKE lower(concat('%',:query,'%') )  " +
            "OR lower(r.description) LIKE lower(concat('%',:query,'%') )"


    )
    List<Restaurent> findBySearchQuery(String query);

    Restaurent findByOwnerId(long userId);









}
