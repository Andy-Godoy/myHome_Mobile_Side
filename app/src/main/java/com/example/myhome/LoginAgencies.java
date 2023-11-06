package com.example.myhome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginAgencies extends AppCompatActivity implements LoginCallback {

    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_agencies);

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
                // Los datos son válidos, puedes proceder con la autenticación o lo que sea necesario.

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
        // Puedes utilizar una expresión regular para validar el formato del correo electrónico.
        // Este es un ejemplo simple, puedes ajustarlo según tus necesidades.
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private boolean isValidPassword(String password) {
        // Puedes establecer tus propias reglas de validación de contraseña aquí.
        // Por ejemplo, asegurarte de que tenga al menos 6 caracteres.
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
}
