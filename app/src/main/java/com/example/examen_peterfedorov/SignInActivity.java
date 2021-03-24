package com.example.examen_peterfedorov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity
{
    private EditText email, password;
    private Button signInButton;
    private Retrofit retrofit;
    private API api;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su/").build();
        api = retrofit.create(API.class);
        email = findViewById(R.id.emailTextSignIn);
        password = findViewById(R.id.passwordTextSignIn);
        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SignIn();
            }
        });
    }
    private boolean isEmailValid(String email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void SignIn()
    {
        if(email.getText().toString().equals("") || password.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Введите данные!", Toast.LENGTH_SHORT).show();
        }
        else if(!isEmailValid(email.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Не корректная почта!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ParamSignIn paramSignIn = new ParamSignIn();
            paramSignIn.setEmail(email.getText().toString());
            paramSignIn.setPassword(password.getText().toString());
            Call<ParamSignIn> call = api.postSignIn(paramSignIn);
            call.enqueue(new Callback<ParamSignIn>() {
                @Override
                public void onResponse(Call<ParamSignIn> call, Response<ParamSignIn> response)
                {
                    if (response.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(), "Успешный вход!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignInActivity.this, MainScreenActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Произошла ошибка!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ParamSignIn> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}