package com.example.myhome.Front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.asksira.loopingviewpager.LoopingViewPager;
import android.widget.Toast;
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.Ignore.ImageSliderAdapter;
import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.FiltersDTO;
import com.example.myhome.model.Properties;
import com.example.myhome.model.PropertySummary;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;


public class ListUserProperties extends AppCompatActivity  implements PropertiesCallback {

    private LinearLayout cardConteiner;
    private List<PropertySummary> properties;
    private Long userId;
    private Long agencyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_properties);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        // Obtenemos el ID del ítem de menú correspondiente a esta actividad
        int menuItemId = R.id.action_home;

        // Marcar el ítem del menú como seleccionado
        bottomNavigationView.setSelectedItemId(menuItemId);

        // Configurar el listener para los elementos del menú
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            MenuHandlerUsuario.handleMenuItemClick(this, item, this.getClass());
            return true;
        });

        cardConteiner = findViewById(R.id.cardContainer);

        FiltersDTO filters = new FiltersDTO();

        PropertyApi propertyApi = new PropertyApi();
        properties = propertyApi.verPropiedades(filters, this);

            //PONER BOTON FILTERS ACA

        Button btnFilters = findViewById(R.id.btnFilters);
        btnFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListUserProperties.this, FilterUserProperties.class);
                startActivityForResult(intent, 1);



            }
        });



//        LoopingViewPager imageSliderSlider = findViewById(R.id.imageSliderSlider);
//
//        // Aquí debes obtener la lista de URLs de tus imágenes en el bucket de Azure
//        List<String> imageUrls = obtenerUrlsDesdeAzure();
//
//        // Crear un adaptador para el LoopingViewPager
//        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this, imageUrls);
//
//        // Establecer el adaptador en el LoopingViewPager
//        imageSliderSlider.setAdapter(imageSliderAdapter);
    }


    private List<String> obtenerUrlsDesdeAzure(String[] propertyImages) {
        List<String> imageUrls = new ArrayList<>();
        if (propertyImages != null) {
            for (String i : propertyImages) {

                imageUrls.add(i);

            }

        }
            return imageUrls;
    }


    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {
        if (properties != null) {
            for (PropertySummary p : properties) {

                View propertyCard = LayoutInflater.from(this).inflate(R.layout.card_property_user, cardConteiner, false);
                List<String> imageUrls = obtenerUrlsDesdeAzure(p.getPropertyImages());

                ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this, imageUrls);

                ((TextView) propertyCard.findViewById(R.id.propertyPrice)).setText("USD ".concat(p.getPropertyPrice().toString()));
                ((TextView) propertyCard.findViewById(R.id.propertyAddress)).setText(p.getPropertyAddress());
                ((TextView) propertyCard.findViewById(R.id.propertyLocation)).setText(p.getPropertyNeighbourhood().concat(", ").concat(p.getPropertyCity()));
                ((TextView) propertyCard.findViewById(R.id.propertyDimensions)).setText(p.getPropertyDimension().toString().concat(" M2"));
                ((TextView) propertyCard.findViewById(R.id.propertyRooms)).setText(p.getPropertyBedroomQuantity().toString().concat(" Habitaciones"));
                ((TextView) propertyCard.findViewById(R.id.propertyDescription)).setText(p.getPropertyDescription());
                ((LoopingViewPager) propertyCard.findViewById(R.id.imageSliderSlider)).setAdapter(imageSliderAdapter);

                propertyCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Obtengo el ID de la propiedad
                        String propertyId = p.getPropertyId().toString();

                        // Iniciar la actividad DetailProperty y paso el ID como extra
                        Intent intent = new Intent(ListUserProperties.this, DetailUserProperty.class);
                        intent.putExtra("propertyId", propertyId);
                        startActivity(intent);
                    }
                });

                ImageView imageProperty = propertyCard.findViewById(R.id.propertyImage);
                String imageUrl = "https://static1.sosiva451.com/521961_a/8b07c18b-b15d-4d23-9bf1-e3d4ce2eea5e_small.jpg";
                Picasso.get().load(imageUrl).into(imageProperty);
                cardConteiner.addView(propertyCard);

            }

}
}

    @Override
    public void onPropertiesSuccess(Properties propiedad) {

    }

    @Override
    public void onPropertiesFailure(String errorMessage) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Aquí puedes realizar la actualización según los datos recibidos
                if (data != null) {
                    FiltersDTO filters = (FiltersDTO) data.getSerializableExtra("filters");
                    PropertyApi propertyApi = new PropertyApi();
                    properties = propertyApi.verPropiedades(filters, this);
                    Toast.makeText(getApplicationContext(), "Filtros Aplicados", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onPropertiesSuccess(Long propertyId) {
    }

}
