package com.example.myhome.Front;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListFavoriteProperties extends AppCompatActivity  implements PropertiesCallback {

    private LinearLayout cardConteiner;
    private List<PropertySummary> properties;
    private Long userId;
    private Long agencyId;

    private double latitude;
    private double longitude;
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
                        // Haz algo con latitude y longitude aquí
                    } else {
                        latitude = 0;
                        longitude = 0;
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
            }
        }

        FiltersDTO filters = new FiltersDTO();
        filters.setUserLatitude(latitude);
        filters.setUserLongitude(longitude);
        filters.setIsFavorite(true);
        if (((MyHome) this.getApplication()).getUsuario() != null) {
            userId = ((MyHome) this.getApplication()).getUsuario().getUserId();
            filters.setUserId(userId);
        }
        PropertyApi propertyApi = new PropertyApi();
        properties = propertyApi.verPropiedades(filters, this);


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

    private List<String> obtenerUrlsDesdeAzure() {
        // Lógica para obtener las URLs de las imágenes desde tu bucket de Azure
        // Puedes implementar la lógica específica para tu aplicación aquí
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://storagemyhome.blob.core.windows.net/containermyhome/casa1.jpg");
        imageUrls.add("https://storagemyhome.blob.core.windows.net/containermyhome/casa2.jpg");
        imageUrls.add("https://storagemyhome.blob.core.windows.net/containermyhome/casa3.jpg");
        // Agrega más URLs según sea necesario
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