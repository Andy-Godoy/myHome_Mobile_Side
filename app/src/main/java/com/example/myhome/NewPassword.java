package com.example.myhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class NewPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
    }
    public void Aceptar(View view) {
        Intent Aceptar=new Intent(NewPassword.this, LoginAgencies.class);
        startActivity(Aceptar);
    }
}
