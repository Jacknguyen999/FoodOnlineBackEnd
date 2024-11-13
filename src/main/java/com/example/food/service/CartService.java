package com.example.food.service;

import com.example.food.model.Cart;
import com.example.food.model.CartItem;
import com.example.food.model.User;
import com.example.food.request.AddCartRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartRequest req, String jwt) throws Exception;

    CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws Exception;


    Cart removeItemFromCart(Long cartItemId,String jwt) throws Exception;

    Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    Cart findCartByUserId(Long userId) throws Exception;

    Cart clearCart(Long userId) throws Exception;
}
