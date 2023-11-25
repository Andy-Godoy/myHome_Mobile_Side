package com.example.myhome.Api;

import android.app.Application;

import com.example.myhome.model.Users;

public class MyHome extends Application {

    private Users usuario;

    public Users getUsuario() {
        return usuario;
    }

    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }
}
