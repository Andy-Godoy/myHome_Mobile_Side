package com.example.myhome.Api;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.Interfaces.RetrofitAPI;

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
        // Configuramos Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realizamos la solicitud

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
                // Acá manejamos los errores de conexión
                Toast.makeText(PropertyApi.this, "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

        return properties;
    }

    public Properties setPropiedades(Properties propiedad, final PropertiesCallback callback) {
        // Configura Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crea una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realiza la solicitud

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
                // Maneja errores de conexión aquí
                Toast.makeText(PropertyApi.this, "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

        return propiedad;
    }

    public Properties obtenerPropiedad(PropertyDTO property, final PropertiesCallback callback) {
        // Configuramos Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realizamos la solicitud
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
                // Acá manejamos los errores de conexión
                Toast.makeText(getApplicationContext(), "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

        return this.property;
    }

    public void eliminarPropiedad( Long idPropiedad, Long agencyId, final PropertiesCallback callback) {

        // Configuramos Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realizamos la solicitud

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
                // Acá manejamos los errores de conexión
                Toast.makeText(PropertyApi.this, "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public Properties editarPropiedad (Properties property, Long agencyId ,final PropertiesCallback callback) {
        // Configuramos Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realizamos la solicitud
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
                // Acá manejamos los errores de conexión
                Toast.makeText(getApplicationContext(), "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

        return this.property;
    }
}