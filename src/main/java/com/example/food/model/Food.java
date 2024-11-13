package com.example.food.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String description;

    private long price;

    @ManyToOne
    private Category foodCategory;

    @Column(length = 100)
    @ElementCollection
    private List<String> images;

    private boolean available;

    @ManyToOne
    private Restaurent restaurent;

    private boolean isVegetarian ;
    private boolean isSeasonal;


    @ManyToMany
    private List<IngredientsItem> ingredients = new ArrayList<>();


    private Date creationDate;


}
