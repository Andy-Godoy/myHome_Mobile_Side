package com.example.myhome.Front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.UsersApi;
import com.example.myhome.Interfaces.LoginCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.BasicCredentials;
import com.example.myhome.model.Users;

public class LoginAgencies extends AppCompatActivity implements LoginCallback {

    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_agencies);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void onLoginButtonClick(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


            if (email.isEmpty()) {
                // Mostrar mensaje de error si el campo de correo electrónico está vacío
                Toast.makeText(this, "El campo de correo electrónico está vacío", Toast.LENGTH_SHORT).show();
            } else if (isValidEmail(email) && isValidPassword(password)) {
                // Cuando ambos datos son válidos, autenticamos o lo que sea necesario.

                BasicCredentials credentials = new BasicCredentials(email, password);
                UsersApi userApi = new UsersApi();
                Users user = userApi.loginUsuario(credentials, this);

            } else {
                // Mostrar mensajes de validación en un AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error de validación");

                if (!isValidEmail(email)) {
                    builder.setMessage("Correo electrónico no válido");
                }

                if (!isValidPassword(password)) {
                    builder.setMessage("Contraseña no válida. Debe contener al menos 6 caracteres.");
                }

                builder.setPositiveButton("OK", null);
                builder.show();
            }
        }
    private boolean isValidEmail(String email) {
        // Usamos una expresión regular para validar el formato del mail

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.*[a-z]*";
        return email.matches(emailPattern);
    }

    private boolean isValidPassword(String password) {

        // Validamos que la pass tenga al menos 6 caracteres.
        return password.length() >= 6;
    }

    public void registrese (View view) {
        Intent miIntent=new Intent(LoginAgencies.this, RegisterAgencies.class);
        startActivity(miIntent);
    }
    public void volver (View view) {
        Intent miIntent=new Intent(LoginAgencies.this, LoginUser.class);
        startActivity(miIntent);
    }
    public void olvido (View view) {
        Intent miIntent=new Intent(LoginAgencies.this, ForgotPassword.class);
        startActivity(miIntent);

    }

    @Override
    public void onLoginSuccess(Users user) {

        ((MyHome) this.getApplication()).setUsuario(user);

        Toast.makeText(this, "Iniciando sesión...", Toast.LENGTH_SHORT).show();
        Intent miIntent=new Intent(LoginAgencies.this, ListAgencieProperties.class);
        startActivity(miIntent);
    }

    @Override
    public void onLoginFailure(String errorMessage) {
        Toast.makeText(this, "No ha podido iniciar sesión", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnregisterSuccess() {

    }
}
