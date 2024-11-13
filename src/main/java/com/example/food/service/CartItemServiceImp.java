package com.example.food.service;

import com.example.food.model.Cart;
import com.example.food.model.CartItem;
import com.example.food.model.Food;
import com.example.food.model.User;
import com.example.food.repository.CartItemRepository;
import com.example.food.repository.CartRepository;
import com.example.food.repository.FoodRepository;
import com.example.food.request.AddCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartItemServiceImp implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.FindFoodByID(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem : cart.getItem()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setFood(food);
        cartItem.setCart(cart);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setIngredients(req.getIngredients());
        cartItem.setTotalprice(req.getQuantity()*food.getPrice());
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        cart.getItem().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw  new Exception("CartItem not found");
        }

        CartItem cartItem = cartItemOptional.get();
        cartItem.setQuantity(quantity);
        cartItem.setTotalprice(cartItem.getFood().getPrice()*quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw  new Exception("CartItem not found");
        }
        CartItem cartItem = cartItemOptional.get();
        cart.getItem().remove(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total = 0L;
        for(CartItem cartItem : cart.getItem()){
            total += cartItem.getFood().getPrice()*cartItem.getQuantity();

        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(optionalCart.isEmpty()){
            throw  new Exception("CartItem not found");
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);

        Cart cart =  cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);
        Cart cart = findCartByUserId(userId);
        cart.getItem().clear();
        return cartRepository.save(cart);
    }
}
