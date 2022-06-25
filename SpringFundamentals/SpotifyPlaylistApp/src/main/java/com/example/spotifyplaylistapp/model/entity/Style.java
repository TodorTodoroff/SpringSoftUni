package com.example.spotifyplaylistapp.model.entity;


import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private StyleTypeEnum styleName;

    private String description;


    public Style() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StyleTypeEnum getStyleName() {
        return styleName;
    }

    public void setStyleName(StyleTypeEnum styleName) {
        this.styleName = styleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
