package com.example.battleships.services;

import com.example.battleships.model.User;
import com.example.battleships.model.dto.UserLoginDTO;
import com.example.battleships.model.dto.UserRegisterDTO;
import com.example.battleships.model.map.MapUser;
import com.example.battleships.repositories.UserRepository;
import com.example.battleships.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final MapUser mapUser;
    private final LoggedUser userSession;

    @Autowired
    public AuthService(UserRepository userRepository, MapUser mapUser, LoggedUser userSession) {

        this.userRepository = userRepository;

        this.mapUser = mapUser;
        this.userSession = userSession;
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

        Optional<User> user = this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (!user.isPresent()) {
            return false;
        }
        this.userSession.login(user.get());

        return true;
    }

    public void logout() {
        this.userSession.logout();
    }



    public User findUserById(Long id) {

        return this.userRepository.findById(id).get();
    }
}
