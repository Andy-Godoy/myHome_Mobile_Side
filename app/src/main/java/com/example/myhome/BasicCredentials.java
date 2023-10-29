package com.example.myhome;

public class BasicCredentials {
    private String userEmail;
    private String userPassword;

    public BasicCredentials(String s, String s1) {
        this.userEmail = s;
        this.userPassword = s1;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
