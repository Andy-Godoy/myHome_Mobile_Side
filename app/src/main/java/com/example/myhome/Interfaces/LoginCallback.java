package com.example.myhome.Interfaces;

import com.example.myhome.Api.Users;

public interface LoginCallback {
    void onLoginSuccess(Users user);
    void onLoginFailure(String errorMessage);
}