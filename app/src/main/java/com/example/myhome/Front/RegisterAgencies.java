package com.example.myhome.Front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.model.BasicCredentials;
import com.example.myhome.Api.MyHome;
import com.example.myhome.model.Users;
import com.example.myhome.Api.UsersApi;
import com.example.myhome.Interfaces.LoginCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;

public class RegisterAgencies extends AppCompatActivity implements LoginCallback {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button validarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_agencies);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        validarButton = findViewById(R.id.validarButton);

        validarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenemos el texto ingresado en los campos de correo y contraseña
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                //validamos mail
                if (!isValidEmail(email)) {
                    showToast("Correo electrónico no válido");
                }
                // Validamos la contraseña
                if (!isValidPassword(password)) {
                    showToast("La contraseña debe tener al menos 6 caracteres");
                }

                // Validamos el mail y la contraseña
                if (isValidEmail(email) && isValidPassword(password)) {


                    // Ambos datos son válidos, navegamos de vuelta a la actividad "LoginRealState"

                    BasicCredentials basicCredentials = new BasicCredentials(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                    intentarRegistrar(basicCredentials);



                }
            }

        });
    }


    private void intentarRegistrar(BasicCredentials credentials) {

        UsersApi userApi = new UsersApi();
        Users user = userApi.registrarUsuario (credentials, this);
    }

    // Función para validar el formato del mail
    private boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    // Función para validar la contraseña
    private boolean isValidPassword(String password) {
        return password.length() >= 6; // Por ejemplo, mínimo 6 caracteres
    }

    // Función para mostrar un mensaje Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void volver (View view) {
        Intent miIntent=new Intent(RegisterAgencies.this, LoginUser.class);
        startActivity(miIntent);
    }

    @Override
    public void onLoginSuccess(Users user) {

        ((MyHome) this.getApplication()).setUsuario(user);

        showToast("usuario registrado correctamente");
        Intent intent = new Intent(RegisterAgencies.this, ListAgencieProperties.class);
        startActivity(intent);
        finish(); // Finalizamos la actividad actual para evitar que el usuario regrese a ella con el botón "Atrás"

    }

    @Override
    public void onLoginFailure(String errorMessage) {
        showToast(errorMessage);
    }
}
