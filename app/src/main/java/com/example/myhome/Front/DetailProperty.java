package com.example.myhome.Front;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.List;

public class DetailProperty extends AppCompatActivity implements PropertiesCallback {
    private Properties propiedad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_property);

        LoopingViewPager imageSlider = findViewById(R.id.imageSlider); // Reemplaza R.id.imageSlider con el ID real de tu LoopingViewPager
        List<String> imageUrls = obtenerUrlsDesdeAzure();

        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this, imageUrls);
        imageSlider.setAdapter(imageSliderAdapter);

        obtenerPropiedad();
        Button btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cierra la actividad y vuelve a la actividad anterior
                finish();
            }
        });
    }

    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {
    }

    @Override
    public void onPropertiesSuccess(Properties propiedad) {
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
        String locacion = propiedad.getPropertyAddress().getAddressName() + " " + propiedad.getPropertyAddress().getAddressNumber() + ", " +
                propiedad.getPropertyAddress().getAddressCity() + ", " + propiedad.getPropertyAddress().getAddressFloor() + " " + propiedad.getPropertyAddress().getAddressUnit();

        String pais = propiedad.getPropertyAddress().getAddressCountry() + ", " + propiedad.getPropertyAddress().getAddressState() + ", " + propiedad.getPropertyAddress().getAddressNeighbourhood();
        String moneda = ((MyHome) this.getApplication()).getUsuario().getUserCurrencyPreference().toString();
        String dormitorio = (propiedad.getPropertyBedroomQuantity() <= 1) ? " dormitorio" : " dormitorios";
        String banio = (propiedad.getPropertyBathroomQuantity() <= 1) ? " banio" : " banios";
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
        String terrace = (propiedad.getpropertyHasTerrace()) ? "Tiene Terraza" : "No tiene Terraza";

        // Convertir la cadena en un array utilizando la coma como delimitador
        String[] amenitiesArray = propiedad.getPropertyAmenities();

        for (int i = 0; i < amenitiesArray.length; i++) {
            if (i == 0) {
                amenities = "+" + amenitiesArray[i];
            } else {
                amenities += "\n + " + amenitiesArray[i];
            }
        }

        tvEstado.setText(propiedad.getPropertyStatus());
        tvPrecioPropiedad.setText(moneda + " " + propiedad.getPropertyPrice().toString());
        tvPrecioExpensas.setText(moneda + " " + propiedad.getPropertyExpenses().toString());
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
        tvAntiguedad.setText(propiedad.getPropertyAge().toString());
        tvCubierto.setText(cubierto);
        tvSemicubierto.setText(semicubierto);
        tvDescubierto.setText(descubierto);
        tvAmenities.setText(amenities);
        tvDescripcion.setText(propiedad.getPropertyDescription());
        tvBaulera.setText(baulera);
        tvTerrace.setText(terrace);
    }

    @Override
    public void onPropertiesFailure(String errorMessage) {
    }

    @Override
    public void onPropertiesSuccess(Long propertyId) {

    }

    public void obtenerPropiedad() {
        PropertyDTO property = new PropertyDTO();

        if (((MyHome) this.getApplication()).getUsuario() != null) {
            property.setPropertyId(Long.parseLong(getIntent().getStringExtra("propertyId")));
        }

        PropertyApi propertyApi = new PropertyApi();
        propiedad = propertyApi.obtenerPropiedad(property, this);
    }

    public void btnClose(View view) {
        // Este método se llamará tanto desde el Button como desde el ImageButton
        finish();
    }

    private List<String> obtenerUrlsDesdeAzure() {
        // Lógica para obtener las URLs de las imágenes desde el blob de Azure

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://storagemyhome.blob.core.windows.net/containermyhome/casa1.jpg");
        imageUrls.add("https://storagemyhome.blob.core.windows.net/containermyhome/casa2.jpg");
        imageUrls.add("https://storagemyhome.blob.core.windows.net/containermyhome/casa3.jpg");

        return imageUrls;
    }
}
