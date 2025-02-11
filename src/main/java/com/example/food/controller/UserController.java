package com.example.food.controller;

import com.example.food.config.JwtProvider;
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


    // moi them vao cho viec change role
    private final JwtProvider jwtProvider;

    // moi them vao cho viec change role
    @Autowired
    public UserController(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }




  /*  @PutMapping("/change_role")
    public ResponseEntity<AuthResponse> changeRole(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        user.setRole(USER_ROLE.ROLE_RESTAURANT_OWNER);
        User savedUser = userRepository.save(user);

        // Generate a new JWT with updated roles
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, savedUser.getAuthorities());
        String newJwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(newJwt);
        authResponse.setMessage("ROLE UPDATED SUCCESSFULLY");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }*/


    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


}
