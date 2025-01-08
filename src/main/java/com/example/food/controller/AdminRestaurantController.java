package com.example.food.controller;


import com.corundumstudio.socketio.SocketIOServer;
import com.example.food.config.SocketIOConfig;
import com.example.food.model.Restaurent;
import com.example.food.model.User;
import com.example.food.request.CreateRestaurantRequest;
import com.example.food.response.MessageResponse;
import com.example.food.service.RestaurantService;
import com.example.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @Autowired
    private SocketIOServer socket;


    @PostMapping()
    public ResponseEntity<Restaurent> createRestaurant
        (@RequestBody CreateRestaurantRequest req,
        @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Restaurent restaurent = restaurantService.createRestaurent(req, user);

    return new ResponseEntity<>(restaurent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurent> updateRestaurent
            (@RequestBody CreateRestaurantRequest req,
             @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Restaurent restaurent = restaurantService.updateRestaurent(id,req);

        return new ResponseEntity<>(restaurent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurent(
             @RequestHeader("Authorization") String jwt,
             @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        restaurantService.deleteRestaurent(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMesssage("Restaurent deleted");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurent> updateRestaurentStatus
            (
             @RequestHeader("Authorization") String jwt,
             @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
       Restaurent restaurent= restaurantService.updateRestaurentStatus(id);
//       socket.getBroadcastOperations().sendEvent("restaurantStatusUpdated", restaurent);



        return new ResponseEntity<>(restaurent, HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<Restaurent> findRestaurantByUserId
            (
             @RequestHeader("Authorization") String jwt
             ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Restaurent restaurent= restaurantService.getRestaurantByUserId(user.getId());


        return new ResponseEntity<>(restaurent, HttpStatus.OK);
    }
}
