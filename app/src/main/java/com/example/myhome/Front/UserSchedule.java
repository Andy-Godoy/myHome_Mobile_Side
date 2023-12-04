package com.example.myhome.Front;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Api.MyHome;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.Users;
import com.squareup.picasso.Picasso;


public class UserSchedule extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageViewProfile;
    private Spinner tipoContacto;
    private Button btnContact;
    private EditText name;
    private EditText email;
    private Users user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_schedule);

        tipoContacto = findViewById(R.id.spnTipoContacto);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);



        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // mostramos mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        if (((MyHome) this.getApplication()).getUsuario() != null) {
            user = ((MyHome) this.getApplication()).getUsuario();
            name.setText(user.getUserName());
            email.setText(user.getUserEmail());
            String imageUrl = getIntent().getStringExtra("agencyImage");
            if (imageUrl == null || imageUrl.equals("")) {

                imageUrl = "https://storagemyhome.blob.core.windows.net/containermyhome/nodisponible.jpg";
            }

            Picasso.get().load(imageUrl).into(imageViewProfile);


        }

        btnContact = findViewById(R.id.btnContactar);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Mostramos un mensaje de advertencia al usuario
                AlertDialog.Builder builder = new AlertDialog.Builder(UserSchedule.this);
                builder.setTitle("Contactar Agencia");
                builder.setMessage("Se enviará un mensaje a la agencia con el tipo de contacto seleccionado");
                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences preferences = getSharedPreferences("mispreferencias", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();

                        //  lo llevamos al activity DetailProperty
                        Intent intent = new Intent(UserSchedule.this, ListUserProperties.class);
                        startActivity(intent);
                        finish(); //  Finaliza la actividad actual

                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // No hace nada
                    }
                });
                builder.show();
            }
        });

    }

    public void volver (View v){

        finish(); //  Finaliza la actividad actual

    }

}