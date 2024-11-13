package com.example.food.controller;

import com.example.food.model.USER_ROLE;
import com.example.food.model.User;
import com.example.food.repository.UserRepository;
import com.example.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @PutMapping("/change_role")
    public ResponseEntity<User> changeRole(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        user.setRole(USER_ROLE.ROLE_RESTAURANT_OWNER);
        User savedUser = userRepository.save(user);
        return new  ResponseEntity<>(savedUser,HttpStatus.OK);
    }
    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


}
