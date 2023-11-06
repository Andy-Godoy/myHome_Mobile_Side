package com.example.myhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewPassword extends AppCompatActivity implements LoginCallback{

    private EditText newPassword;
    private EditText confirmPassword;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

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
        // Puedes establecer tus propias reglas de validación de contraseña aquí.
        // Por ejemplo, asegurarte de que tenga al menos 6 caracteres.
        return password.length() >= 6;
    }
}
