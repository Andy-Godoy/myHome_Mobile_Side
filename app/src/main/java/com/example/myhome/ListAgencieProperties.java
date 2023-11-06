package com.example.myhome;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;



public class ListAgencieProperties extends AppCompatActivity implements PropertiesCallback {

        private Button btnPropiedades;
        private Button btnNuevaPropiedad;
        private Button btnPerfil;
        private LinearLayout cardConteiner;
        private List<PropertySummary> properties;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_agency_main);

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

            // Obtén el ID del ítem de menú correspondiente a esta actividad
            int menuItemId = R.id.action_home; // Reemplaza con el ID correcto para esta actividad

            // Marcar el ítem del menú como seleccionado
            bottomNavigationView.setSelectedItemId(menuItemId);

            // Configurar el listener para los elementos del menú
            bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                MenuHandler.handleMenuItemClick(this, item);
                return true;
            });

            cardConteiner = findViewById(R.id.cardContainer);

//            Log.i("TAG", "onCreate: " + ((MyHome) this.getApplication()).getUsuario().getAgencyId());
            FiltersDTO filters = new FiltersDTO();

            if (((MyHome) this.getApplication()).getUsuario() != null) {
                filters.setAgencyId(((MyHome) this.getApplication()).getUsuario().getAgencyId());
            }

            PropertyApi propertyApi = new PropertyApi();
            properties = propertyApi.verPropiedades(filters, this);


        }

        public void verPropiedades (View view){

            Intent miIntent=new Intent(ListAgencieProperties.this, ListAgencieProperties.class);
            startActivity(miIntent);
        }

        public void nuevaPropiedad (View view){

            Intent miIntent=new Intent(ListAgencieProperties.this, NewProperties.class);
            startActivity(miIntent);
        }

        public void verPerfil (View view){

            Intent miIntent=new Intent(ListAgencieProperties.this, AgenciesProfile.class);
            startActivity(miIntent);
        }

    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {
        if (properties != null){
            for (PropertySummary p: properties){

                View propertyCard = LayoutInflater.from(this).inflate(R.layout.card_property, cardConteiner, false);

                ((TextView) propertyCard.findViewById(R.id.propertyPrice)).setText("USD ".concat(p.getPropertyPrice().toString()));
                ((TextView) propertyCard.findViewById(R.id.propertyAddress)).setText(p.getPropertyAddress());
                ((TextView) propertyCard.findViewById(R.id.propertyLocation)).setText(p.getPropertyNeighbourhood().concat(", ").concat(p.getPropertyCity()));
                ((TextView) propertyCard.findViewById(R.id.propertyDimensions)).setText(p.getPropertyDimension().toString().concat(" M2"));
                ((TextView) propertyCard.findViewById(R.id.propertyRooms)).setText(p.getPropertyBedroomQuantity().toString().concat(" Habitaciones"));
                ((TextView) propertyCard.findViewById(R.id.propertyDescription)).setText(p.getPropertyDescription());

                cardConteiner.addView(propertyCard);

            }


        }

    }

    @Override
    public void onPropertiesSuccess(Properties propiedad) {
    }

    @Override
    public void onPropertiesFailure(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}