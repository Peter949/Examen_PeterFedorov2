package com.example.examen_peterfedorov;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainScreenActivity extends AppCompatActivity
{
    private RecyclerView rec1, rec2, rec3;
    private List<ParamFilm> mFilms1, mFilms2, mFilms3;
    private API api;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su/").build();
        api = retrofit.create(API.class);
        rec1 = findViewById(R.id.rec1);
        rec2 = findViewById(R.id.rec2);
        rec3 = findViewById(R.id.rec3);
        mFilms1 = new ArrayList<>();
        FilmAdapter adapter = new FilmAdapter(mFilms1);
        rec1.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        rec1.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        mFilms2 = new ArrayList<>();
        FilmAdapter adapter2 = new FilmAdapter(mFilms2);
        rec2.setAdapter(adapter2);
        rec2.setLayoutManager(layoutManager2);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        mFilms3 = new ArrayList<>();
        FilmAdapter adapter3 = new FilmAdapter(mFilms3);
        rec3.setAdapter(adapter3);
        rec3.setLayoutManager(layoutManager3);
        getFilms();
    }
    private void getFilms()
    {
        Call<List<ParamFilm>> call = api.getFilm();
        call.enqueue(new Callback<List<ParamFilm>>() {
            @Override
            public void onResponse(Call<List<ParamFilm>> call, Response<List<ParamFilm>> response)
            {
                if(response.isSuccessful())
                {
                    mFilms1.addAll(response.body());
                    mFilms2.addAll(response.body());
                    mFilms3.addAll(response.body());
                    rec1.getAdapter().notifyDataSetChanged();
                    rec2.getAdapter().notifyDataSetChanged();
                    rec3.getAdapter().notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Произошла ошибка!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ParamFilm>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}