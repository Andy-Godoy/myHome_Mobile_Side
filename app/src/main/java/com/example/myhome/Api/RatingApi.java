package com.example.myhome.Api;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Interfaces.RatingCallback;
import com.example.myhome.model.Resenas;
import com.example.myhome.model.enums.ResenaDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RatingApi extends AppCompatActivity {

    private static final String BASE_URL = "https://myhome.azurewebsites.net/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public List<Resenas> verResenas(long agencyId, final RatingCallback callback) {
        // Configuramos Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realizamos la solicitud

        Call<List<Resenas>> call = apiService.getResenas(agencyId);

        call.enqueue(new Callback<List<Resenas>>() {
            @Override
            public void onResponse(Call<List<Resenas>> call, Response<List<Resenas>> response) {
                if (response.isSuccessful()) {

                   callback.onResenasSuccess(response.body());

                }

            }

            @Override
            public void onFailure(Call<List<Resenas>> call, Throwable t) {
                // Acá manejamos los errores de conexión
                Toast.makeText(RatingApi.this, "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
            }
        });

        List<Resenas> resenas=new ArrayList<>();
        return resenas;
    }

    //Guardar nueva reseña
    public void guardarResena(Long agencyId, Long userID, ResenaDTO resena, final RatingCallback callback) {
        // Configuramos Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realizamos la solicitud

        Call<Void> call = apiService.saveResenas(agencyId, userID, resena);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onResenasSuccess();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("No su pudo guardar su reseña");
                }
        });

    }
}
