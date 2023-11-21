package com.example.myhome.Interfaces;

import com.example.myhome.Api.Agencies;
import com.example.myhome.Api.BasicCredentials;
import com.example.myhome.Api.FiltersDTO;
import com.example.myhome.Api.GoogleCredentials;
import com.example.myhome.Api.Properties;
import com.example.myhome.Api.PropertySummary;
import com.example.myhome.Api.Resenas;
import com.example.myhome.Api.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RetrofitAPI {
    @GET("agencies")
    //Call<Agencies> getAgencies();
    Call<List<Agencies>> getAgencies(@Query("userId") int userId);

    @POST("users")
    Call<Users> registrarUsuario (@Body BasicCredentials basicCredentials);

    @POST("users/passwords")
    Call<Users> resetPassword (@Body BasicCredentials basicCredentials);

    @POST("users/logins")
    Call<Users> loguearUsuario (@Body GoogleCredentials credentials);

    @POST("users/logins")
    Call<Users> loguearUsuario (@Body BasicCredentials credentials);

    @POST("properties")
    Call<Properties> createProperty (@Body Properties property);

//    @GET("properties")
//    Call<Properties> getProperty (@Query("propertyId") long propertyId);

    @GET("properties/{propertyId}")
    Call<Properties> getProperty(@Path("propertyId") long propertyId);

    @POST("properties/filters")
    Call<List<PropertySummary>> getProperties (@Body FiltersDTO filters);

    @DELETE("properties/{propertyId}")
    Call<Void> deleteProperty (@Path("propertyId") long propertyId, @Query("agencyId") long agencyId);

    @PUT("properties/{propertyId}")
    Call<Properties> updateProperty (@Path("propertyId") long propertyId, @Query("agencyId") long agencyId, @Body Properties property);

    @POST("properties")
    Call<Properties> setPropiedades(@Body Properties propiedad, @Query("agencyId") long agencyId);

    @GET("reviews")
    Call<List<Resenas>> getResenas(@Query("agencyId") long agencyId);
}
