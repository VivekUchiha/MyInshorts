package com.example.satsv.myinshorts;

import android.Manifest;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.satsv.myinshorts.Retrofit.ApiClient;
import com.example.satsv.myinshorts.Retrofit.ApiInterface;
import com.example.satsv.myinshorts.model.Article;
import com.example.satsv.myinshorts.model.Inshortmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=1;
    VerticalViewPager verticalViewPager;
    private final static String API_KEY = "cad19300c36a49469bedf4cbb1a361bf";
    private final static String source = "in";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        Update();
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
            }
        });
    }

        void Update()
        {

        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<Inshortmodel> call= apiService.getNews(source,API_KEY);
        call.enqueue(new Callback<Inshortmodel>() {
            @Override
            public void onResponse(Call<Inshortmodel> call, Response<Inshortmodel> response) {
                int statusCode = response.code();
                List<Article> ARTICLE = response.body().getArticles();
                verticalViewPager = (VerticalViewPager) findViewById(R.id.verticleViewPager);
                verticalViewPager.setAdapter(new VerticlePagerAdapter(ARTICLE,getApplicationContext()));


            }

            @Override
            public void onFailure(Call<Inshortmodel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_LONG).show();
            }
        });


    }}

