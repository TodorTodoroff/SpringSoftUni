package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.service.SongService;
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
public class SongController {

    private final LoggedUser loggedUser;
    private final SongService songService;


    @Autowired
    public SongController(LoggedUser loggedUser, SongService songService) {
        this.loggedUser = loggedUser;
        this.songService = songService;
    }

    @ModelAttribute("addSongDTO")
    public AddSongDTO initSong (){
        return new AddSongDTO();
    }

    @GetMapping("/songs/add")
    public String addSong() {

        if (loggedUser.getId() == null) {
            return "redirect:/";
        }
        return "song-add";
    }


    @PostMapping("/songs/add")
    public String post(@Valid AddSongDTO addSongDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.songService.addSong(addSongDTO)) {

            redirectAttributes.addFlashAttribute("addSongDTO", addSongDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addSongDTO", bindingResult);

            return "redirect:/songs/add";
        }


        return "redirect:/home";
    }



}
