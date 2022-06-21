package com.example.coffeeshop.services;

import com.example.coffeeshop.InitialCategorySeeder;
import com.example.coffeeshop.Session.LoggedUser;
import com.example.coffeeshop.model.dto.UserLoginDTO;
import com.example.coffeeshop.model.entites.User;
import com.example.coffeeshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;


    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }


    public boolean login(UserLoginDTO loginDTO) {

        Optional<User> userOpt = this.userRepository.findByUsername(loginDTO.getUsername());

        if(userOpt.isEmpty() || (!userOpt.get().getPassword().equals(loginDTO.getPassword()))){
            return false;
        }

        this.loggedUser.login(userOpt.get());

        return true;
    }
}
