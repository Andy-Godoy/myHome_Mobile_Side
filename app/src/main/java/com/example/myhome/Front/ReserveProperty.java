package com.example.myhome.Front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class ReserveProperty extends AppCompatActivity implements PropertiesCallback {
    private Properties propiedad;

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

        if (((MyHome) this.getApplication()).getUsuario() != null) {
            property.setPropertyId(Long.parseLong(getIntent().getStringExtra("propertyId")));
        }

        PropertyApi propertyApi = new PropertyApi();
        propiedad = propertyApi.obtenerPropiedad(property, this);
    }

    public void onPropertiesSuccess(Properties propiedad) {

        this.propiedad = propiedad;

        //String[] propertyImages = propiedad.getPropertyImages();
        //ImageView imageView = findViewById(R.id.imagenIzquierda);
        //imageView.setImageURI(propiedad.getPropertyImages);
        //TextView tvEstado = findViewById(R.id.tvEstado);
        //tvEstado.setText(propiedad.getPropertyStatus());

        if (propiedad != null) {

            TextView tvLocacion = findViewById(R.id.tvDireccion);
            String locacion = propiedad.getPropertyAddress().getAddressName() + " " + propiedad.getPropertyAddress().getAddressNumber();
            tvLocacion.setText(locacion);

            TextView tvProvincia = findViewById(R.id.tvProvincia);
            String provincia = propiedad.getPropertyAddress().getAddressCity();
            tvProvincia.setText(provincia);

            TextView tvPrecioPropiedad = findViewById(R.id.tvPrecioPropiedad);
            String moneda = ((MyHome) this.getApplication()).getUsuario().getUserCurrencyPreference().toString();
            tvPrecioPropiedad.setText(moneda + " " + propiedad.getPropertyPrice().toString());

            TextView tvMontoReserva = findViewById(R.id.tvMontoReserva);
            float montoReserva = (propiedad.getPropertyPrice() * 10 / 100);
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