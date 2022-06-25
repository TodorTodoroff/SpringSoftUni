package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StyleService {

    private final StyleRepository styleRepository;

    @Autowired
    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public void initSeed() {

        if (this.styleRepository.count() == 0) {

            List<Style> collect = Arrays
                    .stream(StyleTypeEnum.values())
                    .map(sty -> {
                        Style style = new Style();
                        style.setStyleName(sty);
                        if (sty.name().equals("ROCK")){
                            style.setDescription("And the Gods who made metal are with us tonight !!!");
                        }
                        return style;
                    })
                    .collect(Collectors.toList());

            this.styleRepository.saveAll(collect);
        }

    }

    public Style findByStyleName(StyleTypeEnum style) {
        return this.styleRepository.findByStyleName(style);
    }
}
