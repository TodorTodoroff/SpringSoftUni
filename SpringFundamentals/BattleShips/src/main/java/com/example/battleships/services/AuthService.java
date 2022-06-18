package com.example.battleships.services;

import com.example.battleships.model.dto.UserRegisterDTO;
import com.example.battleships.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

private final UserRepository userRepository;

@Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRegisterDTO userRegisterDTO){

    }
}
