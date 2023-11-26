package com.example.myhome.Api;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Interfaces.LoginCallback;
import com.example.myhome.R;
import com.example.myhome.model.BasicCredentials;
import com.example.myhome.model.GoogleCredentials;
import com.example.myhome.model.Users;

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

        //inicializamos las vistas
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        validarButton = findViewById(R.id.validarButton);
        //registrarUsuario();
    }

    public Users registrarUsuario(BasicCredentials basicCredentials, final LoginCallback callback) {
        // Configuramos Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realizamos la solicitud
        Call<Users> call = apiService.registrarUsuario(basicCredentials);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {

                    user = response.body();
                    callback.onLoginSuccess(user);


                } else {
                    //acá validamos si el usuario ya existe
                    callback.onLoginFailure("El usuario ya existe");

                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // Acá manejamos los errores de conexión
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

    public Users loginUsuario (BasicCredentials credentials, final LoginCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realizamos la solicitud
        Call<Users> call = apiService.loguearUsuario(credentials);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {

                    user = response.body();
                    callback.onLoginSuccess(user);
                    // acá manejamos la respuesta
                } else {
                    callback.onLoginFailure("Error al loguear usuario");

                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // Acá manejamos errores de conexión
                Log.i("Loguear Usuario", "onFailure: "+t.getMessage());
                callback.onLoginFailure("Error al loguear usuario");
            }
        });
        return user;
    }

    public Users resetPassword(BasicCredentials credentials, final LoginCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos una instancia de la interfaz ApiService
        RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

        // Realizamos la solicitud
        Call<Users> call = apiService.resetPassword(credentials);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    callback.onLoginSuccess(user);
                    // Acá manejamos la respuesta
                } else {
                    callback.onLoginFailure("No se ha podido resetear la contraseña");

                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // acá manejamos los errores de conexión
                callback.onLoginFailure("No se ha podido resetear la contraseña");
            }
        });
        return user;
    }

        //Eliminar usuario
        public Users deleteUser(Long userId, final LoginCallback callback) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Creamos una instancia de la interfaz ApiService
            RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

            // Realizamos la solicitud
            Call<Void> call = apiService.deleteUser(userId);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        callback.onUnregisterSuccess();
                        // Acá manejamos la respuesta
                    } else {
                        callback.onLoginFailure("No se ha podido eliminar el usuario");

                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    callback.onLoginFailure("No se ha podido eliminar el usuario");
                }

            });
        return user;
    }

}