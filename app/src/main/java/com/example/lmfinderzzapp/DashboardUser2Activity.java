package com.example.lmfinderzzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lmfinderzzapp.databinding.ActivityDashboardUser2Binding;
import com.example.lmfinderzzapp.databinding.ActivityDashboardUser4Binding;

public class DashboardUser2Activity extends AppCompatActivity {

    //view binding
    private ActivityDashboardUser2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardUser2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //handle click back button, go to google maps screen
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardUser2Activity.this, HomeUserActivity.class));
                finish();
            }
        });

        //handle click Forward button, go to google maps screen
        binding.forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardUser2Activity.this, HomeUserActivity.class));
                finish();
            }
        });



    }
}