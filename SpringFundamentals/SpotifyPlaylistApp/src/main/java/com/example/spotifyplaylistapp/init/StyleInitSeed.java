package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StyleInitSeed implements CommandLineRunner {

   private final StyleService styleService;

   @Autowired
    public StyleInitSeed(StyleService styleService) {
        this.styleService = styleService;
    }


    @Override
    public void run(String... args) throws Exception {

       this.styleService.initSeed();

    }
}
