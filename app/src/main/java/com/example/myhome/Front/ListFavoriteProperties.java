package com.example.myhome.Front;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.asksira.loopingviewpager.LoopingViewPager;
import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.Ignore.ImageSliderAdapter;
import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.FiltersDTO;
import com.example.myhome.model.Properties;
import com.example.myhome.model.PropertySummary;
import com.example.myhome.model.enums.CurrencyType;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListFavoriteProperties extends AppCompatActivity  implements PropertiesCallback {

    private LinearLayout cardConteiner;
    private List<PropertySummary> properties;
    private Long userId;
    private Long agencyId;
    private final float TIPO_CAMBIO_PESOS = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_favorite_user);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Obtenemos el ID del ítem de menú correspondiente a esta actividad
        int menuItemId = R.id.action_favorite;

        // Marcar el ítem del menú como seleccionado
        bottomNavigationView.setSelectedItemId(menuItemId);

        // Configurar el listener para los elementos del menú
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            MenuHandlerUsuario.handleMenuItemClick(this, item, this.getClass());
            return true;
        });

        cardConteiner = findViewById(R.id.cardContainer);



        FiltersDTO filters = new FiltersDTO();

        filters.setIsFavorite(true);
        if (((MyHome) this.getApplication()).getUsuario() != null) {
            userId = ((MyHome) this.getApplication()).getUsuario().getUserId();
            filters.setUserId(userId);
        }
        PropertyApi propertyApi = new PropertyApi();
        properties = propertyApi.verPropiedades(filters, this);


    }

    private List<String> obtenerUrlsDesdeAzure(String[] propertyImages) {
        List<String> imageUrls = new ArrayList<>();
        if (propertyImages != null) {
            Collections.addAll(imageUrls, propertyImages);

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

                String moneda = ((MyHome) this.getApplication()).getUsuario().getUserCurrencyPreference().toString();
                Integer valorPropiedad = (Integer) Math.round(p.getPropertyPrice() * ((moneda.equals("USD"))?1:TIPO_CAMBIO_PESOS));
                ((TextView) propertyCard.findViewById(R.id.propertyPrice)).setText(moneda + " " + valorPropiedad);
                ((TextView) propertyCard.findViewById(R.id.propertyAddress)).setText(p.getPropertyAddress());
                ((TextView) propertyCard.findViewById(R.id.propertyLocation)).setText(p.getPropertyNeighbourhood().concat(", ").concat(p.getPropertyCity()));
                ((TextView) propertyCard.findViewById(R.id.propertyDimensions)).setText(p.getPropertyDimension().toString().concat(" M2"));
                ((TextView) propertyCard.findViewById(R.id.propertyRooms)).setText(p.getPropertyBedroomQuantity().toString().concat(" Habitaciones"));
                ((TextView) propertyCard.findViewById(R.id.propertyDescription)).setText(p.getPropertyDescription());
                ((LoopingViewPager) propertyCard.findViewById(R.id.imageSliderSlider)).setAdapter(imageSliderAdapter);

                ImageView imageProperty = propertyCard.findViewById(R.id.propertyImage);
                String imageUrl = "https://static1.sosiva451.com/521961_a/8b07c18b-b15d-4d23-9bf1-e3d4ce2eea5e_small.jpg";
                Picasso.get().load(imageUrl).into(imageProperty);
                cardConteiner.addView(propertyCard);

                propertyCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Obtengo el ID de la propiedad
                        String propertyId = p.getPropertyId().toString();

                        // Iniciar la actividad DetailProperty y paso el ID como extra
                        Intent intent = new Intent(ListFavoriteProperties.this, DetailUserProperty.class);
                        intent.putExtra("propertyId", propertyId);
                        startActivity(intent);
                    }
                });

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
    public void onPropertiesSuccess(Long propertyId) {

    }


}