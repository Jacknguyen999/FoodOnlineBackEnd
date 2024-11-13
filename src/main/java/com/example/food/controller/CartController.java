package com.example.food.controller;

import com.example.food.model.Cart;
import com.example.food.model.CartItem;
import com.example.food.model.User;
import com.example.food.request.AddCartRequest;
import com.example.food.request.UpdateCartItemReqeust;
import com.example.food.service.CartService;
import com.example.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(
        @RequestBody AddCartRequest req,
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        CartItem cartItem = cartService.addItemToCart(req,jwt);
        return ResponseEntity.ok(cartItem);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @RequestBody UpdateCartItemReqeust req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return ResponseEntity.ok(cartItem);

    }
    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        Cart cart = cartService.removeItemFromCart(id,jwt);
        return ResponseEntity.ok(cart);

    }
    @DeleteMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return ResponseEntity.ok(cart);

    }
    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return ResponseEntity.ok(cart);

    }

}
