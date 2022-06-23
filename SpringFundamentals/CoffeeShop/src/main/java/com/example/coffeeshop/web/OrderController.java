package com.example.coffeeshop.web;

import com.example.coffeeshop.Session.LoggedUser;
import com.example.coffeeshop.model.dto.AddOrderDTO;
import com.example.coffeeshop.services.CategoryService;
import com.example.coffeeshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OrderController {

    private final LoggedUser loggedUser;
    private final CategoryService categoryService;
    private final OrderService orderService;

    @Autowired
    public OrderController(LoggedUser loggedUser, CategoryService categoryService, OrderService orderService) {
        this.loggedUser = loggedUser;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }


    @ModelAttribute("addOrderDTO")
    public AddOrderDTO initAddOffer() {
        return new AddOrderDTO();
    }


    @GetMapping("/offer-add")
    public String addOffer(Model model) {
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }
        return "order-add";
    }


    @PostMapping("/offer-add")
    public String addOffer(@Valid AddOrderDTO addOrderDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("addOrderDTO", addOrderDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addOrderDTO", bindingResult);

            return "redirect:/offer-add";
        }

        this.orderService.saveOrder(addOrderDTO);

        return "redirect:/home";
    }

}
