package com.example.coffeeshop.web;

import com.example.coffeeshop.Session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;

    public HomeController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @GetMapping("/home")
    public String home() {
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        return "home";
    }


}
