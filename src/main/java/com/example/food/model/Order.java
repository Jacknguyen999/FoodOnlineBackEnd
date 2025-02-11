package com.example.food.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private User customer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurent restaurent;

    private Long totalAmount;

    private String orderStatus;

    private Date createdAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Address deliveryAddress;


    @OneToMany
    private List<OrderItem> items;

//    private Payment paymentmethod;

    private int totalItem;

    private long totalPrice;


}
