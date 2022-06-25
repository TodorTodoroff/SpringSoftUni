package com.example.spotifyplaylistapp.model.dto;


import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class AddSongDTO {

    @NotEmpty
    @Size(min = 3, max = 20)
    private String performer;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String title;

    @PastOrPresent
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    //not null by default - int not Integer
    @Positive
    private int duration;

    @NotNull
    private StyleTypeEnum style;

    public AddSongDTO() {
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public StyleTypeEnum getStyle() {
        return style;
    }

    public void setStyle(StyleTypeEnum style) {
        this.style = style;
    }
}
