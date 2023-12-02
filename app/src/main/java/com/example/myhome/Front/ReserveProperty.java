package com.example.myhome.Front;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;

public class ReserveProperty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_property);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // mostramos mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }
    }
}