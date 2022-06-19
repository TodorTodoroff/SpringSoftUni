package com.example.battleships.services;

import com.example.battleships.model.User;
import com.example.battleships.model.dto.UserRegisterDTO;
import com.example.battleships.model.mapper.UserMapper;
import com.example.battleships.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public AuthService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;

        this.userMapper = userMapper;
    }

    public void register(UserRegisterDTO userRegisterDTO) {

        userValidation(userRegisterDTO);

        User user = this.userMapper.userDtoToUser(userRegisterDTO);

        this.userRepository.save(user);

    }

    private void userValidation(UserRegisterDTO userRegisterDTO) {
        Optional<User> username = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        Optional<User> email = this.userRepository.findByEmail(userRegisterDTO.getEmail());

        if (username.isPresent() || email.isPresent()) {
            throw new RuntimeException("Username or email already in use!");
        }

        if (!userRegisterDTO.getConfirmPassword().equals(userRegisterDTO.getPassword())) {
            throw new RuntimeException("Passwords do not match!");
        }
    }
}
