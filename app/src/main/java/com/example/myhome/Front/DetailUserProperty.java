package com.example.myhome.Front;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.Ignore.ImageSliderAdapter;
import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.R;
import com.example.myhome.model.Properties;
import com.example.myhome.model.PropertyDTO;
import com.example.myhome.model.PropertySummary;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class DetailUserProperty extends AppCompatActivity implements PropertiesCallback {
    private Properties propiedad;
    private FloatingActionButton favoriteButton;
    private boolean isFavorite = false;
    private final float TIPO_CAMBIO_PESOS = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_property);

        favoriteButton = findViewById(R.id.favoriteButton);
        LoopingViewPager imageSlider = findViewById(R.id.imageSlider);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Configuramos el listener para los elementos del menú
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            MenuHandlerUsuario.handleMenuItemClick(this, item, this.getClass());
            return true;
        });


        obtenerPropiedad();
        FloatingActionButton fabShare = findViewById(R.id.fabShare);
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compartirContenido();
            }
        });


        Button btnContactar = findViewById(R.id.btnContactar);
        btnContactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  lo llevamos al activity DetailProperty
                Intent intent = new Intent(DetailUserProperty.this, UserSchedule.class);
                intent.putExtra("agencyImage", (propiedad.getAgencyImage()==null)?"":propiedad.getAgencyImage().toString());
                startActivity(intent);
                finish(); //  Finaliza la actividad actual
            }
        });

        Button btnReservar = findViewById(R.id.btnReservar);
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  lo llevamos al activity DetailProperty
                Intent intent = new Intent(DetailUserProperty.this, ReserveProperty.class);
                intent.putExtra("propertyId", propiedad.getPropertyId().toString());
                startActivity(intent);
                finish(); //  Finaliza la actividad actual
            }
        });
    }



    @Override
    public void onPropertiesSuccess(Properties propiedad) {
        this.propiedad = propiedad;

        String[] propertyImages = propiedad.getPropertyImages();

        LoopingViewPager imageSlider = findViewById(R.id.imageSlider);
        if (propertyImages != null) {
            ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this, Arrays.asList(propertyImages));
            imageSlider.setAdapter(imageSliderAdapter);
        }

        isFavorite = propiedad.getPropertyIsFavorite();
        favoriteButton.setImageResource((isFavorite)?R.drawable.baseline_favorite_24:R.drawable.ic_heart_empty);

        TextView tvEstado = findViewById(R.id.tvEstado);
        TextView tvPrecioPropiedad = findViewById(R.id.tvPrecioPropiedad);
        TextView tvPrecioExpensas = findViewById(R.id.tvPrecioExpensas);
        TextView tvLocacion = findViewById(R.id.tvLocacion);
        TextView tvPais = findViewById(R.id.tvPais);
        TextView tvTipoPropiedad = findViewById(R.id.tvTipoPropiedad);
        TextView tvAmbientes = findViewById(R.id.tvAmbientes);
        TextView tvDormitorios = findViewById(R.id.tvDormitorios);
        TextView tvBanios = findViewById(R.id.tvBanios);
        TextView tvCocheras = findViewById(R.id.tvCocheras);
        TextView tvPisos = findViewById(R.id.tvPisos);
        TextView tvOrientacion = findViewById(R.id.tvOrientacion);
        TextView tvDisposicion = findViewById(R.id.tvDisposicion);
        TextView tvAntiguedad = findViewById(R.id.tvAntiguedad);
        TextView tvCubierto = findViewById(R.id.tvCubierto);
        TextView tvSemicubierto = findViewById(R.id.tvSemiCubierto);
        TextView tvDescubierto = findViewById(R.id.tvDesCubierto);
        TextView tvAmenities = findViewById(R.id.tvAmenities);
        TextView tvDescripcion = findViewById(R.id.tvDescripcion);
        TextView tvBaulera = findViewById(R.id.tvBaulera);
        TextView tvTerrace = findViewById(R.id.tvTerraza);
        TextView tvBalcon = findViewById(R.id.tvBalcon);
        String locacion = propiedad.getPropertyAddress().getAddressName() + " " + propiedad.getPropertyAddress().getAddressNumber() + ", " +
                propiedad.getPropertyAddress().getAddressCity() + ", " + propiedad.getPropertyAddress().getAddressFloor() + " " + propiedad.getPropertyAddress().getAddressUnit();

        String pais = propiedad.getPropertyAddress().getAddressCountry() + ", " + propiedad.getPropertyAddress().getAddressState() + ", " + propiedad.getPropertyAddress().getAddressNeighbourhood();

        String moneda = "USD";
        if (((MyHome) this.getApplication()).getUsuario() != null) {
            moneda = ((MyHome) this.getApplication()).getUsuario().getUserCurrencyPreference().toString();
        }

        String dormitorio = (propiedad.getPropertyBedroomQuantity() <= 1) ? " dormitorio" : " dormitorios";
        String banio = (propiedad.getPropertyBathroomQuantity() <= 1) ? " baño" : " baños";
        String cochera = "";
        String piso = (propiedad.getPropertyAddress().getAddressFloor() <= 1) ? " piso" : " pisos";

        if (propiedad.getPropertyHasGarage()) {
            cochera = "Sin cochera";
        } else {
            cochera = (propiedad.getPropertyGarageQuantity() <= 1) ? " cochera" : " cocheras";
        }

        String cubierto = propiedad.getPropertyCoveredM2() + " m2 \ncubierto";
        String semicubierto = propiedad.getPropertySemiCoveredM2() + " m2 \nsemicubie.";
        String descubierto = propiedad.getPropertyUncoveredM2() + " m2 \ndescubie.";
        String amenities = "";
        String baulera = (propiedad.getPropertyHasStorage()) ? "Tiene Baulera" : "No tiene Baulera";
        String terrace = (propiedad.getPropertyHasTerrace()) ? "Tiene Terraza" : "No tiene Terraza";
        String balcony = (propiedad.getPropertyHasBalcony()) ? "Tiene Balcón" : "No tiene Balcón";

        // Convertir la cadena en un array utilizando la coma como delimitador
        String[] amenitiesArray = propiedad.getPropertyAmenities();

        for (int i = 0; i < amenitiesArray.length; i++) {
            if (i == 0) {
                amenities = " + " + amenitiesArray[i];
            } else {
                amenities += "\n + " + amenitiesArray[i];
            }
        }

        tvEstado.setText(propiedad.getPropertyStatus());
        Integer valorPropiedad = (Integer) Math.round(propiedad.getPropertyPrice() * ((moneda.equals("USD"))?1:TIPO_CAMBIO_PESOS));
        tvPrecioPropiedad.setText(moneda + " " + valorPropiedad);

        Integer valorExpensas = (Integer) Math.round(propiedad.getPropertyExpenses() * ((moneda.equals("USD"))?1:TIPO_CAMBIO_PESOS));
        tvPrecioExpensas.setText(moneda + " " + valorExpensas);

        tvLocacion.setText(locacion);
        tvPais.setText(pais);
        tvTipoPropiedad.setText(propiedad.getPropertyType());
        tvAmbientes.setText("Más de " + propiedad.getPropertyRoomQuantity().toString() + " ambientes");
        tvDormitorios.setText(propiedad.getPropertyBedroomQuantity().toString() + dormitorio);
        tvBanios.setText(propiedad.getPropertyBathroomQuantity().toString() + banio);
        tvCocheras.setText(propiedad.getPropertyGarageQuantity().toString() + cochera);
        tvPisos.setText(propiedad.getPropertyAddress().getAddressFloor().toString() + piso);
        tvOrientacion.setText(propiedad.getPropertyOrientation());
        tvDisposicion.setText(propiedad.getPropertyPosition());
        tvAntiguedad.setText(propiedad.getPropertyAge());
        tvCubierto.setText(cubierto);
        tvSemicubierto.setText(semicubierto);
        tvDescubierto.setText(descubierto);
        tvAmenities.setText(amenities);
        tvDescripcion.setText(propiedad.getPropertyDescription());
        tvBaulera.setText(baulera);
        tvTerrace.setText(terrace);
        tvBalcon.setText(balcony);
    }

    @Override
    public void onPropertiesFailure(String errorMessage) {
        String mensaje = (isFavorite)?"Agregado a":"Eliminado de";
        Toast.makeText(this, mensaje.concat(errorMessage), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPropertiesSuccess(Long propertyId) {}
    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {}

    public void obtenerPropiedad() {
        PropertyDTO property = new PropertyDTO();

        Long userId = 0L;

        if (((MyHome) this.getApplication()).getUsuario() != null) {
            userId = ((MyHome) this.getApplication()).getUsuario().getUserId();
            property.setPropertyId(Long.parseLong(getIntent().getStringExtra("propertyId")));
        }

        PropertyApi propertyApi = new PropertyApi();
        propiedad = propertyApi.obtenerPropiedad(property, userId,this);
    }

    public void btnClose(View view) {
        // Este método se llamará tanto desde el Button como desde el ImageButton
        finish();
    }

    public void compartirContenido() {
        if (propiedad != null) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_SUBJECT, "MyHome");
            String url = "https://www.myhome.com.ar/properties/" + propiedad.getPropertyId();
            intent.putExtra(Intent.EXTRA_TEXT, "Te comparto esta propiedad: <a href=\""+url+"\">" + url + "</a>");
            startActivity(Intent.createChooser(intent, "Compartir con"));
        } else {
            // Manejo si property es nulo
            Toast.makeText(this, "La propiedad es nula", Toast.LENGTH_SHORT).show();
        }
    }

    public void toggleFavorite(View view) {
        isFavorite = !isFavorite;

        if (isFavorite) {
            favoriteButton.setImageResource(R.drawable.baseline_favorite_24);
            //Toast.makeText(this, "Agregado a favoritos", Toast.LENGTH_SHORT).show();
        } else {
            favoriteButton.setImageResource(R.drawable.ic_heart_empty);
            //Toast.makeText(this, "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
        }


        if (((MyHome) this.getApplication()).getUsuario() != null) {
            Long userId = ((MyHome) this.getApplication()).getUsuario().getUserId();

            //Llamo a retrofit para guardar a propiedad en favoritos
            PropertyApi propertyApi = new PropertyApi();
            propertyApi.updateFavorite(propiedad.getPropertyId(), userId, this);
        }

    }

    public void contactarClick(View view) {
        Intent intent = new Intent(this, UserSchedule.class);
        startActivity(intent);
    }

    public void reservarClick(View view) {
        Intent intent = new Intent(this, ReserveProperty.class);
        startActivity(intent);
    }
    public void onResume() {
        super.onResume();
        obtenerPropiedad();
    }
}


