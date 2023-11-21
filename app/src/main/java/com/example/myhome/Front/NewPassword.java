package com.example.myhome.Front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Api.BasicCredentials;
import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.Users;
import com.example.myhome.Api.UsersApi;
import com.example.myhome.Interfaces.LoginCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;

public class NewPassword extends AppCompatActivity implements LoginCallback {

    private EditText newPassword;
    private EditText confirmPassword;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        newPassword = findViewById(R.id.txtPass1);
        confirmPassword = findViewById(R.id.editTextPassword);
        email = getIntent().getStringExtra("email");

    }
    public void Aceptar(View view) {

        if (!isValidPassword( newPassword.getText().toString())) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
        }else {

            if (newPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                BasicCredentials credentials = new BasicCredentials(email, newPassword.getText().toString());
                UsersApi userApi = new UsersApi();
                Users user = userApi.resetPassword(credentials, this);

            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onLoginSuccess(Users user) {

        ((MyHome) this.getApplication()).setUsuario(user);
        Toast.makeText(this, "Reseteo exitoso", Toast.LENGTH_SHORT).show();
        Intent Aceptar=new Intent(NewPassword.this, LoginAgencies.class);
        startActivity(Aceptar);

    }

    @Override
    public void onLoginFailure(String errorMessage) {

        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();

    }
    private boolean isValidPassword(String password) {
        // validamos que tenga al menos 6 caracteres la contraseña
        return password.length() >= 6;
    }
}
