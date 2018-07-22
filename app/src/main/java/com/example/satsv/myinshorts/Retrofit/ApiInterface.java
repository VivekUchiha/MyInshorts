package com.example.satsv.myinshorts.Retrofit;

import com.example.satsv.myinshorts.model.Inshortmodel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //To get popular tracks
    @GET("top-headlines")
    Call<Inshortmodel> getNews(@Query("country") String country, @Query("apiKey") String apiKey);

}