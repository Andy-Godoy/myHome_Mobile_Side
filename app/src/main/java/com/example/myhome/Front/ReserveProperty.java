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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ctc.wstx.util.StringUtil;
import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.Api.RatingApi;
import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.Interfaces.RatingCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.Properties;
import com.example.myhome.model.PropertyDTO;
import com.example.myhome.model.PropertySummary;
import com.example.myhome.model.Resenas;
import com.example.myhome.model.enums.ResenaDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReserveProperty extends AppCompatActivity implements PropertiesCallback, RatingCallback {
    private Properties propiedad;
    private final float PORCENTAJE_COMISION = 0.1F;
    private final float TIPO_CAMBIO_PESOS = 1000;
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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Configuramos el listener para los elementos del menú
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            MenuHandlerUsuario.handleMenuItemClick(this, item, this.getClass());
            return true;
        });

        obtenerPropiedad();

    }

    public void obtenerPropiedad() {
        PropertyDTO property = new PropertyDTO();

        Long userId = 0l;

        if (((MyHome) this.getApplication()).getUsuario() != null) {
            userId = ((MyHome) this.getApplication()).getUsuario().getUserId();
            property.setPropertyId(Long.parseLong(getIntent().getStringExtra("propertyId")));
        }

        PropertyApi propertyApi = new PropertyApi();
        propiedad = propertyApi.obtenerPropiedad(property, userId, this);
    }

    public void onPropertiesSuccess(Properties propiedad) {

        this.propiedad = propiedad;
        if (propiedad != null) {

            ImageView imageView = findViewById(R.id.imagenIzquierda);
            String[] propertyImages = propiedad.getPropertyImages();
            if (propertyImages != null && propertyImages.length > 0 && !propertyImages[0].equals("")) {
                Picasso.get().load(propertyImages[0]).into(imageView);
            }else{
                Picasso.get().load("https://storagemyhome.blob.core.windows.net/containermyhome/nodisponible.jpg").into(imageView);
            }

            ImageView imageViewProfile = findViewById(R.id.imageViewProfile);
            String agencyImages = propiedad.getAgencyImage();
            if (agencyImages != null) {
                Picasso.get().load(agencyImages).into(imageViewProfile);
            }else{
                Picasso.get().load("https://storagemyhome.blob.core.windows.net/containermyhome/nodisponible.jpg").into(imageView);
            }

            TextView tvLocacion = findViewById(R.id.tvDireccion);
            String locacion = propiedad.getPropertyAddress().getAddressName() + " " + propiedad.getPropertyAddress().getAddressNumber();
            tvLocacion.setText(locacion);

            TextView tvProvincia = findViewById(R.id.tvProvincia);
            String provincia = propiedad.getPropertyAddress().getAddressCity();
            tvProvincia.setText(provincia);

            TextView tvPrecioPropiedad = findViewById(R.id.tvPrecioPropiedad);
            String moneda = ((MyHome) this.getApplication()).getUsuario().getUserCurrencyPreference().toString();
            Integer valorPropiedad = (Integer) Math.round(propiedad.getPropertyPrice() * ((moneda.equals("USD"))?1:TIPO_CAMBIO_PESOS));
            tvPrecioPropiedad.setText(moneda + " " + valorPropiedad);

            TextView tvMontoReserva = findViewById(R.id.tvMontoReserva);
            float montoReserva = (valorPropiedad * PORCENTAJE_COMISION);
            tvMontoReserva.setText(moneda + " " + montoReserva);
        }


    }

    @Override
    public void onPropertiesFailure(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPropertiesSuccess(Long propertyId) {
        showCustomDialog();
    }
    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {}

    public void volver(View view) {
        finish();
    }

    public void btnReservar(View view) {
        PropertyApi propertyApi = new PropertyApi();
        propertyApi.reservarPropiedad(propiedad.getPropertyId(), this);
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

                ResenaDTO resena = new ResenaDTO();
                resena.setReviewComment(comentario);
                resena.setReviewScore(Character.getNumericValue(rating.charAt(0)));

                if (((MyHome) ReserveProperty.this.getApplication()).getUsuario() != null) {
                    Long userId = ((MyHome) ReserveProperty.this.getApplication()).getUsuario().getUserId();
                    RatingApi ratingApi = new RatingApi();
                    ratingApi.guardarResena(propiedad.getAgencyId(), userId, resena, ReserveProperty.this);
                }

            }
        });

        dialog.show();
    }

    @Override
    public void onResenasSuccess(List<Resenas> body) {

    }

    @Override
    public void onResenasSuccess() {
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

    @Override
    public void onFailure(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();

    }
}