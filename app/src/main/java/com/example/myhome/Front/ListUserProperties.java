package com.example.myhome.Front;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUserProperties extends AppCompatActivity implements PropertiesCallback {

    private LinearLayout cardContainer;
    private List<PropertySummary> properties;
    private double latitude;
    private double longitude;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private final float TIPO_CAMBIO_PESOS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_properties);

        Button btnFilters = findViewById(R.id.btnFilters);
        btnFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListUserProperties.this, FilterUserProperties.class);
                startActivityForResult(intent, 1);
            }
        });

        // Mover la solicitud de permisos al método onCreate
        requestLocationPermission();

        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            NetworkUtils.showNoInternetMessage(this);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        int menuItemId = R.id.action_home;
        bottomNavigationView.setSelectedItemId(menuItemId);

        // Configurar el listener para los elementos del menú
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            MenuHandlerUsuario.handleMenuItemClick(this, item, this.getClass());
            return true;
        });

        cardContainer = findViewById(R.id.cardContainer);

        // Mover la carga de propiedades después de la solicitud de permisos
        loadProperties(null);
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        if (!checkLocationPermission()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            obtenerUbicacion();
        }
    }

    private void obtenerUbicacion() {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Utiliza un LocationCallback para recibir actualizaciones de ubicación en tiempo real
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null && locationResult.getLastLocation() != null) {
                    Location location = locationResult.getLastLocation();
                    ((MyHome) ListUserProperties.this.getApplication()).setLatitude(location.getLatitude());
                    ((MyHome) ListUserProperties.this.getApplication()).setLongitude(location.getLongitude());

                    // Después de obtener la ubicación, carga las propiedades

                } else {
                    Toast.makeText(ListUserProperties.this, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show();
                    ((MyHome) ListUserProperties.this.getApplication()).setLatitude(0.0);
                    ((MyHome) ListUserProperties.this.getApplication()).setLongitude(0.0);
                    // Aunque no se pueda obtener la ubicación, intenta cargar las propiedades

                }
            }
        };

        // Solicita actualizaciones de ubicación
        client.requestLocationUpdates(createLocationRequest(), locationCallback, null);
    }

    // Método para crear una solicitud de ubicación
    private LocationRequest createLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000); // Intervalo de actualización en milisegundos
        return locationRequest;
    }

    // Muevo la carga de propiedades a un método separado
    private void loadProperties(FiltersDTO filters) {
        if (filters == null) {
            filters = new FiltersDTO();
        }

        filters.setUserLatitude(((MyHome) this.getApplication()).getLatitude());
        filters.setUserLongitude(((MyHome) this.getApplication()).getLongitude());

        PropertyApi propertyApi = new PropertyApi();
        properties = propertyApi.verPropiedades(filters, this);

    }

    // Este método lo llamamos después de que el usuario responde a la solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // El usuario concedió los permisos, ahora tomamos la ubicación
                obtenerUbicacion();
            } else {
                Toast.makeText(this, "Permiso denegado, permite la ubicación", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private List<String> obtenerUrlsDesdeAzure(String[] propertyImages) {
        List<String> imageUrls = new ArrayList<>();
        if (propertyImages != null && propertyImages.length > 0) {
            for (String i : propertyImages) {
                imageUrls.add(i);
            }

        }else{
            imageUrls.add("https://storagemyhome.blob.core.windows.net/containermyhome/nodisponible.jpg");
        }
        return imageUrls;
    }


    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {
        cardContainer.removeAllViews();
        if (properties != null) {
            for (PropertySummary p : properties) {
                View propertyCard = LayoutInflater.from(this).inflate(R.layout.card_property_user, cardContainer, false);
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

                propertyCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String propertyId = p.getPropertyId().toString();
                        Intent intent = new Intent(ListUserProperties.this, DetailUserProperty.class);
                        intent.putExtra("propertyId", propertyId);
                        startActivity(intent);
                    }
                });

                ImageView imageProperty = propertyCard.findViewById(R.id.propertyImage);
                String imageUrl = p.getAgencyImage();

                if (imageUrl == null || imageUrl.equals("")) {
                    imageUrl = "https://storagemyhome.blob.core.windows.net/containermyhome/nodisponible.jpg";
                }

                Picasso.get().load(imageUrl).into(imageProperty);

                cardContainer.addView(propertyCard);

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
                  if (data != null) {
                    loadProperties((FiltersDTO) data.getSerializableExtra("filters"));
                    Toast.makeText(getApplicationContext(), "Filtros Aplicados", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onPropertiesSuccess(Long propertyId) {

    }
    public void onResume() {
        super.onResume();
        loadProperties(null);
    }
}
