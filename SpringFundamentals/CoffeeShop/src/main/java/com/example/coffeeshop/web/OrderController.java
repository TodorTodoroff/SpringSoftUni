package com.example.coffeeshop.web;

import com.example.coffeeshop.Session.LoggedUser;
import com.example.coffeeshop.model.dto.AddOfferDTO;
import com.example.coffeeshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OrderController {

    private final LoggedUser loggedUser;
    private final CategoryService categoryService;

    @Autowired
    public OrderController(LoggedUser loggedUser, CategoryService categoryService) {
        this.loggedUser = loggedUser;
        this.categoryService = categoryService;
    }


//    @ModelAttribute("addOfferDTO")
//    public AddOfferDTO initAddOffer() {
//        return new AddOfferDTO();
//    }


    @GetMapping("/offer-add")
    public String addOffer(Model model) {
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("addOfferDTO")){
            model.addAttribute("addOfferDTO", new AddOfferDTO());
        }

        model.addAttribute("categories", this.categoryService.getAllCategories());


        return "order-add";
    }


    @PostMapping("/offer-add")
    public String addOffer(@Valid AddOfferDTO addOfferDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addOfferDTO", bindingResult);

            return "redirect:/offer-add";
        }

        return "redirect:/home";
    }

}
