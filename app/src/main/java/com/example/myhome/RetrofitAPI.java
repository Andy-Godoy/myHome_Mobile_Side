package com.example.myhome;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;




public interface RetrofitAPI {
    @GET("agencies")
    //Call<Agencies> getAgencies();
    Call<List<Agencies>> getAgencies(@Query("userId") int userId);

    @POST("users")
    Call<Users> registrarUsuario (@Body BasicCredentials basicCredentials);

    @POST("users/logins")
    Call<Users> loguearUsuario (@Body GoogleCredentials credentials);

}
