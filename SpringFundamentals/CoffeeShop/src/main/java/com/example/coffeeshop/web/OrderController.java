package com.example.coffeeshop.web;

import com.example.coffeeshop.Session.LoggedUser;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    private final LoggedUser loggedUser;

    @Autowired
    public OrderController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @GetMapping("/offer-add")
    public String addOffer(){
        if (loggedUser.getId() == null){
            return "redirect:/";
        }

        return "order-add";

    }

}
