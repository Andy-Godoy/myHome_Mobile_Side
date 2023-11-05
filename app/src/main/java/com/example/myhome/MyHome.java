package com.example.myhome;

import android.app.Application;

public class MyHome extends Application {

    private Users usuario;

    public Users getUsuario() {
        return usuario;
    }

    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }
}
