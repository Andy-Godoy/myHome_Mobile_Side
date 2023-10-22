package com.example.myhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordCode extends AppCompatActivity {

    private EditText editTextCodigo;
    private TextView mensajeTextView;

    private String codigo; // El código generado en la primera pantalla

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_code);

        editTextCodigo = findViewById(R.id.editTextCodigo);
        mensajeTextView = findViewById(R.id.mensajeTextView);

        // Recupera el código generado de la primera pantalla
        codigo = getIntent().getStringExtra("codigo");

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
            startActivity(miIntent);
            // Aquí puedes habilitar otros elementos de UI para modificar la contraseña.
        } else {
            mensajeTextView.setText("Código incorrecto. Intente de nuevo.");
        }
    }
}

