package com.example.myhome.Api;

import android.widget.Toast;
import com.example.myhome.Interfaces.AgencyCallBack;
import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.model.Agencies;
import com.example.myhome.model.Properties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgencyApi {
    private static final String BASE_URL = "https://myhome.azurewebsites.net/api/v1/";
    public Agencies getAgency(Long agencyId, AgencyCallBack callback) {
        // Configuramos Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realizamos la solicitud

        Call<Agencies> call = apiService.getAgency(agencyId);

        call.enqueue(new Callback<Agencies>() {
            @Override
            public void onResponse(Call<Agencies> call, Response<Agencies> response) {
                if (response.isSuccessful()) {

                    callback.onAgencySuccess(response.body(), false);

                }

            }

            @Override
            public void onFailure(Call<Agencies> call, Throwable t) {
                // Acá manejamos los errores de conexión
                callback.onFailure("No se han podido recuperar los datos de la agencia");
            }
        });

        return null;
    }

    public Agencies editarAgencia (Agencies agency, final AgencyCallBack callback) {
        // Configuramos Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realizamos la solicitud
        Call<Agencies> call = apiService.updateAgency(Long.valueOf(agency.getAgencyId()), agency, Long.valueOf(agency.getUserId()));

        call.enqueue(new Callback<Agencies>() {
            @Override
            public void onResponse(Call<Agencies> call, Response<Agencies> response) {
                if (response.isSuccessful()) {

                    callback.onAgencySuccess(response.body(), true);

                }

            }

            @Override
            public void onFailure(Call<Agencies> call, Throwable t) {
                // Acá manejamos los errores de conexión
                callback.onFailure("Los datos no pudieron ser actualizados. Intentelo nuevamente mas tarde.");
            }
        });

        return null;
    }

}
