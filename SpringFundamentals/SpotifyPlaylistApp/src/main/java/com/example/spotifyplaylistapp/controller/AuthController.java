package com.example.spotifyplaylistapp.controller;


import com.example.spotifyplaylistapp.model.dto.UserLoginDTO;
import com.example.spotifyplaylistapp.model.dto.UserRegisterDTO;
import com.example.spotifyplaylistapp.service.AuthService;
import com.example.spotifyplaylistapp.session_sort_of.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final LoggedUser loggedUser;
    private final AuthService authService;

    @Autowired
    public AuthController(LoggedUser loggedUser, AuthService authService) {
        this.loggedUser = loggedUser;
        this.authService = authService;
    }


    @ModelAttribute("userLoginDTO")
    public UserLoginDTO initLogin() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public String login() {
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }
        return "login";
    }


    @PostMapping("/login")
    public String post(@Valid UserLoginDTO userLoginDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.authService.login(userLoginDTO)) {

            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userLoginDTO", bindingResult);

            return "redirect:/login";
        }

        return "redirect:/home";
    }


    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO initRegister() {
        return new UserRegisterDTO();
    }


    @GetMapping("/register")
    public String register() {
        if (loggedUser.getId() != null){
            return "redirect:/home";
        }

        return "register";
    }


    @PostMapping("/register")
    public String post(@Valid UserRegisterDTO userRegisterDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.authService.registerUser(userRegisterDTO) ) {

            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(){
        this.loggedUser.logout();

        return "redirect:/home";
    }


}
