package com.example.myhome;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class GoogleMap extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private TextView textLocation;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        textLocation = findViewById(R.id.textLocation);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Configuramos la solicitud de ubicación
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Verificamos los permisos de ubicación
        if (checkLocationPermission()) {
            // Si los permisos están ok, osea que los confirmó el usuario, obtenemos la ubicación
            getLocation();
        } else {
            // Si no se dieron los permisos, los solicita
            requestLocationPermission();
        }
    }

    // Verificamos si se tienen los permisos de ubicación activados
    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    // Solicitamos los permisos de ubicación
    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);
    }

    // Manejamos la respuesta de la solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // si el permiso es concedido, obtenemos la ubicación
                getLocation();
            } else {
                // Permiso no concedido, acá tendríamos que mostrar un toast para que le avise al usuario
            }
        }
    }

    // Obtiene nuestra ubicación actual
    private void getLocation() {
        try {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        updateLocationUI(latitude, longitude);
                    } else {
                        // Si la ubicación es nula, puede ser que la ubicación no está disponible.
                        // acá podemos meter los logs que necesitemos para el debug
                    }
                }
            });
        } catch (SecurityException e) {
            e.printStackTrace();
            // y aca manejamos la excepciones
        }
    }

    // Actualizamos la interfaz de usuario con la ubicación obtenida y la muestro en el activity_google_map.xml
    private void updateLocationUI(double latitude, double longitude) {
        String locationText = "Latitud: " + latitude + "\nLongitud: " + longitude;
        textLocation.setText(locationText);
    }
}