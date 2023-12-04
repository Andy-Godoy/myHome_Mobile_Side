package com.example.myhome.Front;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListFavoriteProperties extends AppCompatActivity implements PropertiesCallback {

    private LinearLayout cardConteiner;
    private List<PropertySummary> properties;
    private Long userId;
    private Long agencyId;

    private EditText searchEditText;
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

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                int rootViewHeight = decorView.getHeight();
                int keypadHeight = rootViewHeight - decorView.findViewById(android.R.id.content).getHeight();
                if (keypadHeight > rootViewHeight * 0.15) {
                    // Teclado visible, ocultar BottomNavigationView
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    // Teclado oculto, mostrar BottomNavigationView
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        searchEditText = findViewById(R.id.editTextSearchFavorite);
        cardConteiner = findViewById(R.id.cardContainer);

        cargarDatos();
        setupSearchEditText();
    }

    private void cargarDatos() {
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
        if (propertyImages != null && propertyImages.length > 0) {
            Collections.addAll(imageUrls, propertyImages);

        } else {
            imageUrls.add("https://storagemyhome.blob.core.windows.net/containermyhome/nodisponible.jpg");
        }
        return imageUrls;
    }

    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {
        actualizarVista(properties);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cargarDatos();
    }

    private void closePropertiesActivity() {
        // Tu lógica para cerrar la actividad


        // Configura el resultado para enviar datos de vuelta a la actividad AgenciasPropertiesActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("actualizarDatos", true);
        setResult(RESULT_OK, resultIntent);

        // Cierra la actividad
        finish();
    }

    private void setupSearchEditText() {

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No necesario para tu caso
                Log.d("TAG", "beforeTextChanged: " + charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterProperties(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("TAG", "afterTextChanged: " + editable.toString());
                // No necesario para tu caso
            }
        });
    }

    private void filterProperties(String searchText) {
        List<PropertySummary> filteredProperties = new ArrayList<>();
        if(properties!=null){
            filteredProperties = properties.stream()
                    .filter(property -> propertyContainsText(property, searchText))
                    .collect(Collectors.toList());
        }
        actualizarVista(filteredProperties);
    }

    private boolean propertyContainsText(PropertySummary property, String searchText) {
        // Verifica si la descripción de la propiedad contiene el texto de búsqueda
        String propertyDescription = property.getPropertyAddress().toLowerCase();
        return propertyDescription.contains(searchText.toLowerCase());
    }

    private void actualizarVista(List<PropertySummary> properties) {
        cardConteiner.removeAllViews();

        if (properties != null) {

            if(this.properties == null){
                this.properties = properties;
            }

            for (PropertySummary p : properties) {

                View propertyCard = LayoutInflater.from(this).inflate(R.layout.card_property_user, cardConteiner, false);
                List<String> imageUrls = obtenerUrlsDesdeAzure(p.getPropertyImages());

                ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this, imageUrls);

                if (((MyHome) this.getApplication()).getUsuario() != null) {
                    String moneda = ((MyHome) this.getApplication()).getUsuario().getUserCurrencyPreference().toString();
                    Integer valorPropiedad = (Integer) Math.round(p.getPropertyPrice() * ((moneda.equals("USD")) ? 1 : TIPO_CAMBIO_PESOS));
                    ((TextView) propertyCard.findViewById(R.id.propertyPrice)).setText(moneda + " " + valorPropiedad);
                }else{
                    String moneda = "USD";
                    Integer valorPropiedad = (Integer) Math.round(p.getPropertyPrice() * ((moneda.equals("USD")) ? 1 : TIPO_CAMBIO_PESOS));
                    ((TextView) propertyCard.findViewById(R.id.propertyPrice)).setText(moneda + " " + valorPropiedad);
                }

                ((TextView) propertyCard.findViewById(R.id.propertyAddress)).setText(p.getPropertyAddress());
                ((TextView) propertyCard.findViewById(R.id.propertyLocation)).setText(p.getPropertyNeighbourhood().concat(", ").concat(p.getPropertyCity()));
                ((TextView) propertyCard.findViewById(R.id.propertyDimensions)).setText(p.getPropertyDimension().toString().concat(" M2"));
                ((TextView) propertyCard.findViewById(R.id.propertyRooms)).setText(p.getPropertyBedroomQuantity().toString().concat(" Habitaciones"));
                ((TextView) propertyCard.findViewById(R.id.propertyDescription)).setText(p.getPropertyDescription());
                ((LoopingViewPager) propertyCard.findViewById(R.id.imageSliderSlider)).setAdapter(imageSliderAdapter);

                ImageView imageProperty = propertyCard.findViewById(R.id.propertyImage);
                String imageUrl = p.getAgencyImage();
                if (imageUrl == null) {
                    imageUrl = "https://storagemyhome.blob.core.windows.net/containermyhome/nodisponible.jpg";
                } else {
                    Picasso.get().load(imageUrl).into(imageProperty);
                }

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

}