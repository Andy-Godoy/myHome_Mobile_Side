package com.example.myhome.Api;

import com.example.myhome.model.Agencies;
import com.example.myhome.model.BasicCredentials;
import com.example.myhome.model.FiltersDTO;
import com.example.myhome.model.GoogleCredentials;
import com.example.myhome.model.Properties;
import com.example.myhome.model.PropertySummary;
import com.example.myhome.model.Resenas;
import com.example.myhome.model.Users;
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

    @DELETE("users/{userId}")
    Call<Void> deleteUser (@Path("userId") long userId);

    @POST("properties")
    Call<Properties> createProperty (@Body Properties property);

 //   @GET("properties")
 //   Call<Properties> getProperty (@Query("propertyId") long propertyId);

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

    @GET("agencies/{agencyId}")
    Call<Agencies> getAgency(@Path("agencyId") Long agencyId);

    @PUT("agencies/{agencyId}")
    Call<Agencies> updateAgency(@Path("agencyId") Long agencyId, @Body Agencies agency, @Query("userId") Long userId);

    @PUT("users/{userId}")
    Call<Users> updateUser(@Path("userId") Long userId, @Body Users user);
    @POST("properties/{propertyId}/favorites")
    Call<Void> updateFavorite(@Path ("propertyId") Long propertyId, @Query("userId") long userId);
}
