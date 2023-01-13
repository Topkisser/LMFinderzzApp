package com.example.lmfinderzzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lmfinderzzapp.databinding.ActivityHomeUserBinding;
import com.example.lmfinderzzapp.databinding.ActivityLoginBinding;

public class HomeUserActivity extends AppCompatActivity {


    //view binding
    private ActivityHomeUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //handle click search button, go to google maps screen
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeUserActivity.this, GoogleMapsActivity.class));
                finish();
            }
        });

        //handle click parisionBridgeIV, go to special page
        binding.parisionBridgeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeUserActivity.this, DashboardUser2Activity.class));
                finish();
            }
        });

        //handle click moraineLakeIV, go to special page
        binding.moraineLakeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeUserActivity.this, DashboardUser3Activity.class));
                finish();
            }
        });

        //handle click enchhantedRockIV, go to special page
        binding.enchhantedRockIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeUserActivity.this, DashboardUser4Activity.class));
                finish();
            }
        });

        //handle click theLakeIV, go to special page
        binding.theLakeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeUserActivity.this, DashboardUser5Activity.class));
                finish();
            }
        });

        //handle click back button, go to login page
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeUserActivity.this, LoginActivity.class));
                finish();
            }
        });





    }
}