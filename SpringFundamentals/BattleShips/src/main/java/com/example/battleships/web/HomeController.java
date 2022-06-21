package com.example.battleships.web;

import com.example.battleships.model.dto.FireFxDTO;
import com.example.battleships.services.ShipService;
import com.example.battleships.session.LoggedUser;
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
public class HomeController {

    private final ShipService shipService;
    private final LoggedUser loggedUser;


    @Autowired
    public HomeController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("fireFxModel")
    public FireFxDTO initFireFx(){
        return new FireFxDTO();
    }


    @GetMapping("/")
    public String loggedOutIndex() {
        return "index";
    }

    @GetMapping("/home")
    public String loggedInHome(Model model) {
        if (loggedUser.getId() == null){
            return "redirect:/";
        }

        model.addAttribute("currentUserShips", this.shipService.findAllByUserId(loggedUser.getId()));
        model.addAttribute("otherUsersShips", this.shipService.findOtherUsersShipsExcludingCurrentUser(loggedUser.getId()));
        model.addAttribute("allShipsOrdered", this.shipService.allShipsOrderedByNameHealthPower());

        return "home";
    }


    @PostMapping("/home")
    public String fireFx(@Valid FireFxDTO fireFxModel,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("fireFxModel", fireFxModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.fireFxModel", bindingResult);
            return "redirect:/";
        }

       this.shipService.fireFx(fireFxModel);

        return "redirect:/";
    }

}
