package com.example.myhome.Front;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.example.myhome.Ignore.ImageSliderAdapter;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import java.util.ArrayList;
import java.util.List;

public class ListUserProperties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider_item);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        LoopingViewPager imageSliderSlider = findViewById(R.id.imageSliderSlider);

        // Aquí debes obtener la lista de URLs de tus imágenes en el bucket de Azure
        List<String> imageUrls = obtenerUrlsDesdeAzure();

        // Crear un adaptador para el LoopingViewPager
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this, imageUrls);

        // Establecer el adaptador en el LoopingViewPager
        imageSliderSlider.setAdapter(imageSliderAdapter);
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
}