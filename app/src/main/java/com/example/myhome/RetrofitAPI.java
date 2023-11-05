package com.example.myhome;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
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

    @POST("users/logins")
    Call<Users> loguearUsuario (@Body BasicCredentials credentials);

    @POST("properties")
    Call<Properties> createProperty (@Body Properties property);

    @GET("properties")
    Call<Properties> getProperty (@Query("propertyId") long propertyId);

    @POST("properties")
    Call<List<PropertySummary>> getProperties (@Body Map<String, Object> filters);

    @DELETE("properties")
    Call<Response<Properties>> deleteProperty (@Query("propertyId") long propertyId, @Query("agencyId") long agencyId);

    @PUT("properties")
    Call<Properties> updateProperty (@Body Properties property);


}
