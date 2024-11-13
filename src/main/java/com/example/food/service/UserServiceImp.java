package com.example.food.service;

import com.example.food.config.JwtProvider;
import com.example.food.model.User;
import com.example.food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp  implements  UserService{
@Autowired
    private JwtProvider jwtProvider;
    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
       String email =  jwtProvider.getEmailFromJwtToken(jwt);
       User users = userRepository.findByEmail(email);
        return users;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found");

        }

        return user;
    }
}
