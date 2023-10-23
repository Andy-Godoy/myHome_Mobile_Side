package com.example.myhome;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface RetrofitAPI {
    @GET("agencies")
    //Call<Agencies> getAgencies();
    Call<List<Agencies>> getAgencies(@Query("userId") int userId);
}
