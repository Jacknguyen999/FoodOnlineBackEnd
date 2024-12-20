package com.example.food.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User owner;

    private String name;

    private String description;

    private String cuisineType;


    @OneToOne
    private Address address;

    @Embedded
    private ContactInformation contactInformation;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurent",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    private List<String> images;

    private LocalDateTime registrationDate;

    private boolean open;

    private String OpeningHour;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurent",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Food> foods = new ArrayList<>();



}
