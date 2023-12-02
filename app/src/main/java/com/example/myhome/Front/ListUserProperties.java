package com.example.myhome.Front;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
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
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.Ignore.ImageSliderAdapter;
import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.FiltersDTO;
import com.example.myhome.model.Properties;
import com.example.myhome.model.PropertySummary;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListUserProperties extends AppCompatActivity implements PropertiesCallback {

    private LinearLayout cardConteiner;
    private List<PropertySummary> properties;
    private Long userId;
    private Long agencyId;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_properties);

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

        cardConteiner = findViewById(R.id.cardContainer);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {

            obtenerUbicacion();
        }
    }


    private void obtenerUbicacion() {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        client.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        // Ubicación obtenida con éxito
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                       // Toast.makeText(getApplicationContext(), "Ubicación obtenida con éxito", Toast.LENGTH_SHORT).show();
                        // Haz algo con latitude y longitude aquí
                    } else {

                    }
                });
        }

        // Este método lo llamamos después de que el usuario responde a la solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // El usuario concedió los permisos, ahora tomamos la ubicación
                obtenerUbicacion();
            } else {
                Toast.makeText(this, "Permiso denegado, permite la ubicación", Toast.LENGTH_SHORT).show();
                latitude = 0;
                longitude = 0;
            }
        }


        FiltersDTO filters = new FiltersDTO();
        filters.setUserLatitude(latitude);
        filters.setUserLongitude(longitude);
        PropertyApi propertyApi = new PropertyApi();
        properties = propertyApi.verPropiedades(filters, this);

        Button btnFilters = findViewById(R.id.btnFilters);
        btnFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListUserProperties.this, FilterUserProperties.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private List<String> obtenerUrlsDesdeAzure() {
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://storagemyhome.blob.core.windows.net/containermyhome/casa1.jpg");
        imageUrls.add("https://storagemyhome.blob.core.windows.net/containermyhome/casa2.jpg");
        imageUrls.add("https://storagemyhome.blob.core.windows.net/containermyhome/casa3.jpg");
        return imageUrls;
    }

    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {
        if (properties != null) {
            for (PropertySummary p : properties) {
                View propertyCard = LayoutInflater.from(this).inflate(R.layout.card_property_user, cardConteiner, false);
                List<String> imageUrls = obtenerUrlsDesdeAzure();

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
                        String propertyId = p.getPropertyId().toString();
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
