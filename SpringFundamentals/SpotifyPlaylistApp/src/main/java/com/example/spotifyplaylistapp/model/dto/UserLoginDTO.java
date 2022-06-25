package com.example.spotifyplaylistapp.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserLoginDTO {

    @NotEmpty
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters !")
    private String username;

    @NotEmpty
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters !")
    private String password;

    public UserLoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
