package com.example.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IngredientsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @JsonIgnore
    @ManyToOne
    private Restaurent restaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
    private List<IngredientsItem> ingredients = new ArrayList<>();

}
