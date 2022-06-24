package com.example.coffeeshop.web;

import com.example.coffeeshop.Session.LoggedUser;
import com.example.coffeeshop.model.entites.Order;
import com.example.coffeeshop.services.AuthService;
import com.example.coffeeshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final OrderService orderService;
    private final AuthService authService;

    @Autowired
    public HomeController(LoggedUser loggedUser, OrderService orderService, AuthService authService) {
        this.loggedUser = loggedUser;
        this.orderService = orderService;
        this.authService = authService;
    }


    @GetMapping("/home")
    public String home(Model model) {
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        List<Order> orders = this.orderService.findAllSorted();

        if (!model.containsAttribute("orders")) {
            model.addAttribute("orders", orders);
            model.addAttribute("totalTimeForCompletion",
                    orders
                            .stream()
                            .map(or -> or.getCategory().getNeededTime())
                            .reduce(Integer::sum)
                            .orElse(0));

            model.addAttribute("employees", this.authService.findAllUsersWithOrdersSortedByOrderCount());
        }


        return "home";
    }

    @GetMapping("/orders/ready/{id}")
    public String orderReady(@PathVariable Long id) {

        this.orderService.readyOrder(id);

        return "redirect:/home";
    }


}
