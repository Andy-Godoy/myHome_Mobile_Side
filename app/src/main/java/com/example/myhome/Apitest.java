package com.example.myhome;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Apitest extends AppCompatActivity {

    private TextView agencyEmail, agencyId, userId, agencyName;
    private CardView courseCV;
    private ProgressBar loadingPB;

    private static final String BASE_URL = "https://myhome.azurewebsites.net/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apitest);
        //inicializar vistas
        loadingPB = findViewById(R.id.idLoadingPB);
        agencyEmail = findViewById(R.id.idTVagencyEmail);
        agencyId = findViewById(R.id.idTVagencyId);
        userId = findViewById(R.id.idTVuserId);
        agencyName = findViewById(R.id.idTVagencyName);
        courseCV = findViewById(R.id.idCVCourse);
        getAgencie();
    }



    private void getAgencie() {
        // Configura Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crea una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realiza la solicitud
        Call<List<Agencies>> call = call = apiService.getAgencies(1); // Supongo que deseas userId = 1

        call.enqueue(new Callback<List<Agencies>>() {
            @Override
            public void onResponse(Call<List<Agencies>> call, Response<List<Agencies>> response) {
                if (response.isSuccessful()) {

                    loadingPB.setVisibility(View.GONE);
                    courseCV.setVisibility(View.VISIBLE);
                    List<Agencies> agencies = response.body();
                    agencyEmail.setText(agencies.get(0).getAgencyEmail().toString());
                    agencyId.setText(agencies.get(0).getAgencyId().toString());
                    userId.setText(agencies.get(0).getUserId().toString());
                    agencyName.setText(agencies.get(0).getAgencyName().toString());
                    // Maneja la respuesta aquí
                } else {
                    // Maneja errores de respuesta aquí
                }
            }

            @Override
            public void onFailure(Call<List<Agencies>> call, Throwable t) {
                // Maneja errores de conexión aquí
                Toast.makeText(Apitest.this, "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();

            }
        });
    }


}