package com.example.battleships.web;


import com.example.battleships.model.dto.CreateShipDTO;
import com.example.battleships.services.ShipService;
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
public class ShipController {


    private ShipService shipService;
    private LoggedUser loggedUser;

    @Autowired
    public ShipController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("createShipDTO")
    public CreateShipDTO initCreateShip() {
        return new CreateShipDTO();
    }


    @GetMapping("/ships/add")
    public String ship() {
        if (loggedUser.getId() == null){
            return "redirect:/";
        }
        return "ship-add";
    }


    @PostMapping("/ships/add")
    public String addShip(@Valid CreateShipDTO createShipDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.shipService.createShip(createShipDTO)) {
            redirectAttributes.addFlashAttribute("createShipDTO", createShipDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createShipDTO", bindingResult);

            return "redirect:/ships/add";
        }
        return "redirect:/home";
    }


}
