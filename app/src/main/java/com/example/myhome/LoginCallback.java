package com.example.myhome;

public interface LoginCallback {
    void onLoginSuccess(Users user);
    void onLoginFailure(String errorMessage);
}