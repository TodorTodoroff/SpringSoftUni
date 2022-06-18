package com.example.battleships.model.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterDTO {

    @Size(min = 3, max = 10)
    @NotEmpty
    private String username;

    @Size(min = 5, max = 20)
    @NotEmpty
    private String fullname;

    @Email
    @NotEmpty
    private String email;

    @Size(min = 3)
    @NotEmpty
    private String password;

    @Size(min = 3)
    @NotEmpty
    private String confirmPassword;


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
