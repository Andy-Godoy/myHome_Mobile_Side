package com.example.myhome.Front;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;

public class ReserveProperty extends AppCompatActivity {
    private RatingBar rbAtencion;
    private EditText etComentario;

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

    public void volver(View view) {
        finish();
    }

    public void btnReservar(View view) {
        showCustomDialog();
    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(ReserveProperty.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false); // Evita que el diálogo se cierre tocando fuera de él


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_user_reviews);

        Button btnCloseModal = dialog.findViewById(R.id.btnCloseModal);
        rbAtencion = dialog.findViewById(R.id.rbAtencion);
        etComentario = dialog.findViewById(R.id.txtComentario);
        btnCloseModal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comentario = etComentario.getText().toString();
                String rating = String.valueOf(rbAtencion.getRating());

                Log.d("rbAtencion", rating);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ReserveProperty.this);

                // Configura el título del diálogo
                alertDialogBuilder.setTitle("Reserva realizada");

                // Configura el mensaje del diálogo
                alertDialogBuilder.setMessage("La reserva se ha realizado de forma exitosa.\n" +
                        "En breve, recibirá un mail de confirmación y la inmobiliaria se contactará con usted en las proximas 24hs.");

                // Configura el botón "Aceptar"
                alertDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Regresa a la actividad anterior (ActividadA en este caso)
                        Intent intent = new Intent(ReserveProperty.this, ListUserProperties.class);
                        startActivity(intent);

                        // Cierra la actividad actual si es necesario
                        finish();
                        dialog.dismiss(); // Cierra el diálogo
                    }
                });

                // Crea y muestra el diálogo
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
//                dialog.dismiss();
            }
        });

        dialog.show();
    }
}