package com.example.myhome;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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


public class UsersApi extends AppCompatActivity {

    private TextView editTextEmail, editTextPassword;
    private Button validarButton;

    private Users user;

    private static final String BASE_URL = "https://myhome.azurewebsites.net/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_agencies);

        //inicializar vistas
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        validarButton = findViewById(R.id.validarButton);
        //registrarUsuario();
    }

        public Users registrarUsuario(BasicCredentials basicCredentials) {
            // Configura Retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Crea una instancia de la interfaz ApiService
            RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

            // Realiza la solicitud
            Call<Users> call = apiService.registrarUsuario(basicCredentials); // Supongo que deseas userId = 1

            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.isSuccessful()) {

                        user = response.body();
                        System.out.println(user);


                        // Maneja la respuesta aquí
                    } else {

                        Log.i("Registrar Usuario", "onFailure: "+response.code());
                        Log.i("Registrar Usuario", "onFailure: "+response.body());

                        showToast("El usuario ya existe");


                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    // Maneja errores de conexión aquí
                    Log.i("Registrar Usuario", "onFailure: "+t.getMessage());
                   // Toast.makeText(UsersApi.this, "Falla por un ratito la API :(", Toast.LENGTH_SHORT).show();
                }
            });
            return user;
        }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public Users loginUsuario (GoogleCredentials credentials, final LoginCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crea una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realiza la solicitud
        Call<Users> call = apiService.loguearUsuario(credentials);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {

                    user = response.body();
                    callback.onLoginSuccess(user);
                    // Maneja la respuesta aquí
                } else {
                    callback.onLoginFailure("Error al loguear usuario");

                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // Maneja errores de conexión aquí
                Log.i("Loguear Usuario", "onFailure: "+t.getMessage());
                callback.onLoginFailure("Error al loguear usuario");
            }
        });
        return user;
    }
}