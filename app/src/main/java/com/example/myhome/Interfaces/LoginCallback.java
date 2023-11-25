package com.example.myhome.Interfaces;

import com.example.myhome.model.Users;

public interface LoginCallback {
    default void onLoginSuccess(Users user){};
    void onLoginFailure(String errorMessage);
    void onLoginSuccess();
}