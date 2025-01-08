package com.example.food.repository;

import com.example.food.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem,Long> {

    List<IngredientsItem> findByRestaurentId(Long id);

 /*   @Modifying
    @Transactional
    @Query("DELETE FROM IngredientsItem i WHERE i.restaurent.id = :restaurentId")
    void deleteIngredientsByRestaurantId(@Param("restaurentId") Long restaurentId);*/

}
