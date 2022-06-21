package com.example.battleships.web;

import com.example.battleships.model.dto.UserLoginDTO;
import com.example.battleships.model.dto.UserRegisterDTO;
import com.example.battleships.services.AuthService;
import com.example.battleships.session.LoggedUser;
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

    private AuthService authService;
    private LoggedUser loggedUser;

    @Autowired
    public AuthController(AuthService authService, LoggedUser loggedUser) {
        this.authService = authService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("userRegisterDto")
    public UserRegisterDTO initRegisterForm() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("loginDTO")
    public UserLoginDTO initLoginForm() {
        return new UserLoginDTO();
    }


    @GetMapping("/login")
    public String login() {
        if (loggedUser.getId() != null){
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
        }

        if (!this.authService.login(loginDTO)) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        if (loggedUser.getId() == null){
            return "redirect:/";
        }
        this.authService.logout();
        return "index";
    }


    @GetMapping("/register")
    public String register() {
        if (loggedUser.getId() != null){
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.authService.register(userRegisterDto)) {

            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterDto", bindingResult);

            return "redirect:/register";
        }
        return "redirect:/login";
    }

}
