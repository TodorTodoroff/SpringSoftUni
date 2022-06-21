package com.example.coffeeshop.Session;

import com.example.coffeeshop.model.entites.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class LoggedUser {

    private Long id;

    private String username;

    public LoggedUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void logout() {
        this.id = null;
        this.username = null;
    }

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();

    }

}
