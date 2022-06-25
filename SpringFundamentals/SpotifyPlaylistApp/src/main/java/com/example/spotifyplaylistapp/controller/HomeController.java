package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.service.AuthService;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.session_sort_of.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final SongService songService;
    private final AuthService authService;

    @Autowired
    public HomeController(LoggedUser loggedUser, SongService songService, AuthService authService) {
        this.loggedUser = loggedUser;
        this.songService = songService;
        this.authService = authService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }


        model.addAttribute("popSongs", this.songService.getPopSongs());
        model.addAttribute("rockSongs", this.songService.getRockSongs());
        model.addAttribute("jazzSongs", this.songService.getJazzSongs());
        model.addAttribute("userSongs", this.authService.getCurrentUserSongs(loggedUser.getId()));
        model.addAttribute("totalDuration", this
                .authService
                .getCurrentUserSongs(loggedUser.getId())
                .stream()
                .map(Song::getDuration)
                .reduce(Integer::sum)
                .orElse(0));


        return "home";
    }


    @GetMapping("/song/add/playlist/{id}")
    public String addSongToPlaylist(@PathVariable Long id){

        this.authService.addSong(id);

        return "redirect:/home";
    }

    @GetMapping("/playlist/remove-all")
    public String removeAllSongs(){

        this.authService.removeSongs();

        return "redirect:/home";
    }


}
