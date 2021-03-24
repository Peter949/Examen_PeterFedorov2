package com.example.examen_peterfedorov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieScreenActivity extends AppCompatActivity
{
    private TextView Name, Des, Age;
    private ImageView Image;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_screen);
        Name = findViewById(R.id.name);
        Des = findViewById(R.id.des);
        Age = findViewById(R.id.age);
        Image = findViewById(R.id.image1);
        Intent intent = getIntent();
        Name.setText(intent.getStringExtra("name"));
        Des.setText(intent.getStringExtra("des"));
        Age.setText("Просмотр для лиц старше " + intent.getStringExtra("age") + " лет");
        Picasso.with(getApplicationContext()).load(FilmAdapter.URL + intent.getStringExtra("image")).into(Image);
    }
}