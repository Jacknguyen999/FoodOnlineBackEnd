package com.example.food.service;

import com.example.food.model.*;
import com.example.food.repository.AddressRepository;
import com.example.food.repository.OrderItemRepository;
import com.example.food.repository.OrderRepository;
import com.example.food.repository.UserRepository;
import com.example.food.request.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements  OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private  RestaurantService restaurantService;

    @Autowired
    private CartService cartService;







//    @Override
//    public Order createOrder(CreateOrderRequest order, User user) throws Exception {
//        Address shippingAddress = order.getDeliveryAddress();
//        Address savedAddress = addressRepository.save(shippingAddress);
//        if(!user.getAddresses().contains(savedAddress)) {
//            user.getAddresses().add(savedAddress);
//            userRepository.save(user);
//        }
//        Restaurent restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
//
//        Order createdOrder = new Order();
//        createdOrder.setCustomer(user);
//        createdOrder.setCreatedAt(new Date());
//        createdOrder.setOrderStatus("Pending");
//        createdOrder.setDeliveryAddress(savedAddress);
//        createdOrder.setRestaurent(restaurant);
//
//
//        Cart cart = cartService.findCartByUserId(user.getId());
//
//        List<OrderItem> orderItems = new ArrayList<>();
//
//        for(CartItem cartItem : cart.getItem()){
//            OrderItem orderItem = new OrderItem();
//            orderItem.setQuantity(cartItem.getQuantity());
//            orderItem.setFood(cartItem.getFood());
//            orderItem.setTotalPrice(cartItem.getTotalprice());
//            createdOrder.setTotalItem(orderItem.getQuantity());
//
//            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
//            orderItems.add(savedOrderItem);
//        }
//        Long totalPrice  = cartService.calculateCartTotals(cart);
//            createdOrder.setItems(orderItems);
//            createdOrder.setTotalPrice(totalPrice);
//            Order savedOrder = orderRepository.save(createdOrder);
//            restaurant.getOrders().add(savedOrder);
//
//        return savedOrder;
//    }
@Override
public Order createOrder(CreateOrderRequest order, User user) throws Exception {
    Address shippingAddress = order.getDeliveryAddress();

    // Check if the user's addresses already contain the shipping address
    boolean addressExists = user.getAddresses().stream()
            .anyMatch(address -> address.equals(shippingAddress));

    Address finalShippingAddress;
    if (!addressExists) {
        finalShippingAddress = addressRepository.save(shippingAddress);
        user.getAddresses().add(finalShippingAddress);
        userRepository.save(user);
    } else {
        finalShippingAddress = user.getAddresses().stream()
                .filter(address -> address.equals(shippingAddress))
                .findFirst()
                .orElse(shippingAddress);
    }

    Restaurent restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

    Order createdOrder = new Order();
    createdOrder.setCustomer(user);
    createdOrder.setCreatedAt(new Date());
    createdOrder.setOrderStatus("Pending");
    createdOrder.setDeliveryAddress(finalShippingAddress);
    createdOrder.setRestaurent(restaurant);

    Cart cart = cartService.findCartByUserId(user.getId());

    List<OrderItem> orderItems = new ArrayList<>();

    for (CartItem cartItem : cart.getItem()) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setFood(cartItem.getFood());
        orderItem.setTotalPrice(cartItem.getTotalprice());
        createdOrder.setTotalItem(orderItem.getQuantity());
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
//        orderItem.setOrder(createdOrder);
//        orderItems.add(savedOrderItem);
        orderItems.add(savedOrderItem);
    }

    Long totalPrice = cartService.calculateCartTotals(cart);
    createdOrder.setItems(orderItems);
    createdOrder.setTotalPrice(totalPrice);
    Order savedOrder = orderRepository.save(createdOrder);


    restaurant.getOrders().add(savedOrder);

    return savedOrder;
}

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if( orderStatus.equals("OUT_FOR_DELIVERY") ||
                orderStatus.equals("DELIVERED") ||
                orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING")){

            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("PLS SELECT A VALID ORDER STATUS");

    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);

    }

    @Override
    public List<Order> getUserOrder(Long userId) throws Exception {


        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByRestaurentId(restaurantId);
        if(orderStatus !=null){
            orders = orders.stream().filter(order ->
                    order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;

    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("Order not found");
        }
        return optionalOrder.get();
    }
}
