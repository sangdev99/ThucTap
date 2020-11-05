package com.example.thuctap;

public class Users {

    private String Active ;
    private String DateLoggin ;
    private String Password ;
    private String Username ;

    public Users(String active, String dateLoggin, String password, String username) {
        Active = active;
        DateLoggin = dateLoggin;
        Password = password;
        Username = username;
    }

    public Users() {
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getDateLoggin() {
        return DateLoggin;
    }

    public void setDateLoggin(String dateLoggin) {
        DateLoggin = dateLoggin;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
