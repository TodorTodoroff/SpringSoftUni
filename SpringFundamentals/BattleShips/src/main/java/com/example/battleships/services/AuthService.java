package com.example.battleships.services;

import com.example.battleships.model.User;
import com.example.battleships.model.dto.UserLoginDTO;
import com.example.battleships.model.dto.UserRegisterDTO;
import com.example.battleships.model.map.MapUser;
import com.example.battleships.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final MapUser mapUser;

    @Autowired
    public AuthService(UserRepository userRepository, MapUser mapUser) {

        this.userRepository = userRepository;

        this.mapUser = mapUser;
    }

    public boolean register(UserRegisterDTO userRegisterDTO) {

        if (!userValidation(userRegisterDTO)) {
            return false;
        }

        User user = this.mapUser.userDTOtoUser(userRegisterDTO);

        this.userRepository.save(user);

        return true;
    }

    private boolean userValidation(UserRegisterDTO userRegisterDTO) {
        Optional<User> username = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        Optional<User> email = this.userRepository.findByEmail(userRegisterDTO.getEmail());

        return (!username.isPresent() && !email.isPresent()) && (userRegisterDTO.getConfirmPassword().equals(userRegisterDTO.getPassword()));
    }


    public boolean login(UserLoginDTO loginDTO) {



        return true;
    }
}
