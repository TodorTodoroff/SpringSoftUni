package com.example.coffeeshop.web;

import com.example.coffeeshop.Session.LoggedUser;
import com.example.coffeeshop.model.dto.UserLoginDTO;
import com.example.coffeeshop.model.dto.UserRegisterDTO;
import com.example.coffeeshop.services.AuthService;
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

    public AuthController(LoggedUser loggedUser, AuthService authService) {
        this.loggedUser = loggedUser;
        this.authService = authService;
    }

    @ModelAttribute("loginDto")
    public UserLoginDTO initLogin() {
        return new UserLoginDTO();
    }

    @ModelAttribute("registerDTO")
    public UserRegisterDTO initRegister() {
        return new UserRegisterDTO();
    }

    @GetMapping("/login")
    public String login() {
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }
        return "login";
    }


    @PostMapping("/login")
    public String login(@Valid UserLoginDTO loginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginDto", bindingResult);

            return "redirect:/login";
        }

        if (!this.authService.login(loginDto)) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDto);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";

    }

    @GetMapping("/register")
    public String register() {
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }

        return "register";
    }


    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO registerDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.authService.register(registerDTO)) {

            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";

    }

}