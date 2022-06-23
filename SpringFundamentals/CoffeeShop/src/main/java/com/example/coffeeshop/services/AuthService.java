package com.example.coffeeshop.services;

import com.example.coffeeshop.Session.LoggedUser;
import com.example.coffeeshop.model.dto.UserLoginDTO;
import com.example.coffeeshop.model.dto.UserRegisterDTO;
import com.example.coffeeshop.model.entites.User;
import com.example.coffeeshop.model.mapper.UserMapper;
import com.example.coffeeshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final UserMapper userMapper;


    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser loggedUser, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.userMapper = userMapper;
    }


    public boolean login(UserLoginDTO loginDTO) {

        Optional<User> userOpt = this.userRepository.findByUsername(loginDTO.getUsername());

        if(userOpt.isEmpty() || (!userOpt.get().getPassword().equals(loginDTO.getPassword()))){
            return false;
        }
        this.loggedUser.login(userOpt.get());

        return true;
    }


    public boolean register(UserRegisterDTO registerDTO) {

        Optional<User> userOpt = this.userRepository.findByUsername(registerDTO.getUsername());

        if (userOpt.isPresent() || (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword()))){
            return false;
        }

        User user = this.userMapper.userRegisterDtoToUser(registerDTO);

        this.userRepository.save(user);

        return true;
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).get();
    }

    public List<User> findAllUsersWithOrdersSortedByOrderCount() {

        return this.userRepository.findByOrderByOrderSize();

    }
}
