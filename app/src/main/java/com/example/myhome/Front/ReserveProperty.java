package com.example.myhome.Front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.Properties;
import com.example.myhome.model.PropertyDTO;
import com.example.myhome.model.PropertySummary;
import com.example.myhome.model.enums.CurrencyType;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ReserveProperty extends AppCompatActivity implements PropertiesCallback {
    private Properties propiedad;
    private final float PORCENTAJE_COMISION = 0.1F;
    private final float TIPO_CAMBIO_PESOS = 1000;

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

        Button btnReservar = findViewById(R.id.btnReservar);
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  lo llevamos al activity ListUserProperties pero hay que ir contra la API
                Intent intent = new Intent(ReserveProperty.this, ListUserProperties.class);
                startActivity(intent);
                finish(); //  Finaliza la actividad actual

            }
        });
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
            if (propertyImages != null && propertyImages.length > 0) {
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
        Toast.makeText(this, "Error al recuperar la propiedad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPropertiesSuccess(Long propertyId) {}
    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {}

    public void volver(View view) {
        finish();
    }
}