package com.example.myhome.Api;

import android.app.Application;

import com.example.myhome.model.Users;

public class MyHome extends Application {

    private Users usuario;
    private double latitude;
    private double longitude;

    public Users getUsuario() {
        return usuario;
    }

    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
