package com.example.myhome;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListAgencieProperties extends AppCompatActivity {

        private Button btnPropiedades;
        private Button btnNuevaPropiedad;
        private Button btnPerfil;
        private LinearLayout cardConteiner;


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

//            btnNuevaPropiedad = findViewById(R.id.btnNuevaPropiedad);
//            btnPropiedades = findViewById(R.id.btnPropiedades);
//            btnPerfil = findViewById(R.id.btnPerfil);
            cardConteiner = findViewById(R.id.cardContainer);

            Map<String, Object> filters = new HashMap<>();
            filters.put("agencyId", 1);

            PropertyApi propertyApi = new PropertyApi();
            List<PropertySummary> properties = propertyApi.verPropiedades(filters);

            if (properties != null){

                for (PropertySummary p: properties){

                    View propertyCard = LayoutInflater.from(this).inflate(R.layout.card_property, cardConteiner, false);

                    TextView txtPrecio = propertyCard.findViewById(R.id.propertyPrice);
                    txtPrecio.setText(p.getPropertyPrice());

                    propertyCard.setId(Integer.valueOf(p.getPropertyId().toString()));

                    TextView txtDireccion = propertyCard.findViewById(R.id.propertyAddress);
                    txtDireccion.setText(p.getPropertyAddress());

                    TextView txtCiudad = propertyCard.findViewById(R.id.propertyLocation);
                    txtCiudad.setText(p.getPropertyNeighbourhood().concat(", ").concat(p.getPropertyCity()));

                    TextView txtDimensiones = propertyCard.findViewById(R.id.propertyDimensions);
                    txtDimensiones.setText(p.getPropertyDimension());

                    TextView txtHabitaciones = propertyCard.findViewById(R.id.propertyRooms);
                    txtHabitaciones.setText(p.getPropertyBedroomQuantity());

                    TextView txtDescripcion = propertyCard.findViewById(R.id.propertyDescription);
                    txtDescripcion.setText(p.getPropertyDescription());

                    cardConteiner.addView(propertyCard);


                }

            }

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
    }