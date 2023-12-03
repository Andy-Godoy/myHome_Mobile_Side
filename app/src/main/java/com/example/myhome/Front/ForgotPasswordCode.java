package com.example.myhome.Front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;

public class ForgotPasswordCode extends AppCompatActivity {

    private EditText editTextCodigo;
    private TextView mensajeTextView;
    private String email;

    private String codigo; // El código generado en la primera pantalla

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_code);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        editTextCodigo = findViewById(R.id.editTextCodigo);
        mensajeTextView = findViewById(R.id.mensajeTextView);

        // Recupera el código generado de la primera pantalla
        codigo = getIntent().getStringExtra("codigo");
        email = getIntent().getStringExtra("email");


        Button validarButton = findViewById(R.id.validarButton);
        validarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCodigo();
            }
        });
    }

    private void validarCodigo() {
        String codigoIngresado = editTextCodigo.getText().toString();

        if (codigoIngresado.equals(codigo)) {
            // Código válido, permite modificar la contraseña
            mensajeTextView.setText("");
            Intent miIntent=new Intent(ForgotPasswordCode.this, NewPassword.class);
            miIntent.putExtra("email", email);
            startActivity(miIntent);

        } else {
            mensajeTextView.setText("Código incorrecto. Intente de nuevo.");
        }
    }
}

