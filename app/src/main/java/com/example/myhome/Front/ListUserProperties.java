package com.example.myhome.Front;

import android.net.Uri;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

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
            MenuHandlerUsuario.handleMenuItemClick(this, item);
            return true;
        });

        cardConteiner = findViewById(R.id.cardContainer);

        FiltersDTO filters = new FiltersDTO();

        if (((MyHome) this.getApplication()).getUsuario() != null) {
            agencyId = Long.valueOf(6);
            //((MyHome) this.getApplication()).getUsuario().getAgencyId();
            filters.setAgencyId(agencyId);
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
        if (properties != null){
            for (PropertySummary p: properties){

                View propertyCard = LayoutInflater.from(this).inflate(R.layout.card_property, cardConteiner, false);
                List<String> imageUrls = obtenerUrlsDesdeAzure();

                ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this, imageUrls);

                ((TextView) propertyCard.findViewById(R.id.propertyPrice)).setText("USD ".concat(p.getPropertyPrice().toString()));
                ((TextView) propertyCard.findViewById(R.id.propertyAddress)).setText(p.getPropertyAddress());
                ((TextView) propertyCard.findViewById(R.id.propertyLocation)).setText(p.getPropertyNeighbourhood().concat(", ").concat(p.getPropertyCity()));
                ((TextView) propertyCard.findViewById(R.id.propertyDimensions)).setText(p.getPropertyDimension().toString().concat(" M2"));
                ((TextView) propertyCard.findViewById(R.id.propertyRooms)).setText(p.getPropertyBedroomQuantity().toString().concat(" Habitaciones"));
                ((TextView) propertyCard.findViewById(R.id.propertyDescription)).setText(p.getPropertyDescription());
                ((LoopingViewPager) propertyCard.findViewById(R.id.imageSliderSlider)).setAdapter(imageSliderAdapter);

               ImageView imageProperty =   propertyCard.findViewById(R.id.imageProperty);


                // URL de la imagen
                String imageUrl = "https://static1.sosiva451.com/521961_a/8b07c18b-b15d-4d23-9bf1-e3d4ce2eea5e_small.jpg";
                // Usa Glide para cargar y mostrar la imagen desde la URL
                imageProperty.setImageURI(Uri.parse(imageUrl));

                propertyCard.setId(Integer.valueOf(p.getPropertyId().toString()));


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
    public void onPropertiesSuccess(Long propertyId) {

    }

}