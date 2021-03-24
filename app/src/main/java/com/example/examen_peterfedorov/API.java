package com.example.examen_peterfedorov;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API
{
    @POST("auth/login")
    Call<ParamSignIn> postSignIn(@Body ParamSignIn paramSignIn);

    @GET("movies?filter=new")
    Call<List<ParamFilm>> getFilm();
}
