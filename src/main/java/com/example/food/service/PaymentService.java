package com.example.food.service;

import com.example.food.model.Order;
import com.example.food.response.PaymentResponse;
import com.stripe.exception.StripeException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentService  {

    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
