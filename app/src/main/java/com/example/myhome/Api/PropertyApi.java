package com.example.myhome.Api;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.model.FiltersDTO;
import com.example.myhome.model.Properties;
import com.example.myhome.model.PropertyDTO;
import com.example.myhome.model.PropertySummary;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PropertyApi extends AppCompatActivity {
    private TextView editTextCalle, editTextNumero, editTextDto, editTextPiso, editTextPais, editTextProvincia, editTextLocalidad, editTextBarrio, editTextCantidadAmbientes, editTextCantidadCocheras, editTextCantidadBanios, editTextCantidadCuartos, editTextCubierto, editTextSemiCubierto, editTextDescubierto, editTextPrecioPropiedad, editTextPrecioExpensa;
    private Spinner spnrTipoPropieda, spnrEstado, spnrOrientacion, sprnPosicion, spnrAntiguedad;
    private Switch swTieneTerraza, swTieneCochera, swTieneBaulera, swTeieneBalcon;
    private Button validarButton;
    private List<PropertySummary> properties;
    private Properties property;

    private static final String BASE_URL = "https://myhome.azurewebsites.net/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public List<PropertySummary> verPropiedades(FiltersDTO filters, final PropertiesCallback callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);



        Call<List<PropertySummary>> call = apiService.getProperties(filters);

        call.enqueue(new Callback<List<PropertySummary>>() {
            @Override
            public void onResponse(Call<List<PropertySummary>> call, Response<List<PropertySummary>> response) {
                if (response.isSuccessful()) {
                    callback.onPropertiesSuccess(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<PropertySummary>> call, Throwable t) {

                Toast.makeText(PropertyApi.this, "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

        return properties;
    }

    public Properties setPropiedades(Properties propiedad, final PropertiesCallback callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);



        Call<Properties> call = apiService.setPropiedades(propiedad,propiedad.getAgencyId());

        call.enqueue(new Callback<Properties>() {
            @Override
            public void onResponse(Call<Properties> call, Response<Properties> response) {
                if (response.isSuccessful()) {
                    callback.onPropertiesSuccess(response.body());
                }

            }

            @Override
            public void onFailure(Call<Properties> call, Throwable t) {

                Toast.makeText(PropertyApi.this, "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

        return propiedad;
    }

    public Properties obtenerPropiedad(PropertyDTO property, final PropertiesCallback callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);


        Call<Properties> call = apiService.getProperty(property.getPropertyId());

        call.enqueue(new Callback<Properties>() {
            @Override
            public void onResponse(Call<Properties> call, Response<Properties> response) {
                if (response.isSuccessful()) {
                    callback.onPropertiesSuccess(response.body());
                }

            }

            @Override
            public void onFailure(Call<Properties> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

        //TODO: Devolvemos this.property, que parece ser null en este punto y pincha al compartir el detalle
        return this.property;
    }

    public void eliminarPropiedad( Long idPropiedad, Long agencyId, final PropertiesCallback callback) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);



        Call<Void> call = apiService.deleteProperty(idPropiedad, agencyId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onPropertiesSuccess(idPropiedad);

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(PropertyApi.this, "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public Properties editarPropiedad (Properties property, Long agencyId ,final PropertiesCallback callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);


        Call<Properties> call = apiService.updateProperty(property.getPropertyId(), agencyId, property);

        call.enqueue(new Callback<Properties>() {
            @Override
            public void onResponse(Call<Properties> call, Response<Properties> response) {
                if (response.isSuccessful()) {
                    callback.onPropertiesSuccess(response.body());
                }

            }

            @Override
            public void onFailure(Call<Properties> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

        return this.property;
    }

    // agregar a favoritos

    public void updateFavorite(Long propertyId, Long userId, final PropertiesCallback callback ) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);


        Call<Void> call = apiService.updateFavorite(propertyId, userId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onPropertiesFailure(" favoritos");
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

        return;
    }
}