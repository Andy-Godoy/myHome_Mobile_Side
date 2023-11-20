package com.example.myhome;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.asksira.loopingviewpager.LoopingViewPager;

import java.util.ArrayList;
import java.util.List;

public class ListUserProperties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider_item);

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