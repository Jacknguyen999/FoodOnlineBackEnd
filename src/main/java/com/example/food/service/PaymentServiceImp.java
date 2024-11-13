package com.example.food.service;

import com.example.food.model.Order;
import com.example.food.response.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements  PaymentService{


    @Value("${stripe.api.key}")
    private String stripeSecretKey;



    @Override
    public PaymentResponse createPaymentLink(Order order) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder().addPaymentMethodType(
                SessionCreateParams.
                        PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment/success/" + order.getId())
                .setCancelUrl("http://localhost:3000/payment/failed")
                .addLineItem(SessionCreateParams.LineItem.builder().setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency("VND")
                        .setUnitAmount(order.getTotalPrice() + 40000 + 4000)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Thomas Food").build())
                                .build()
        )
                                .build()
                )
                .build();
        Session session = Session.create(params);

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPayment_url(session.getUrl());
        return paymentResponse;
    }
}
