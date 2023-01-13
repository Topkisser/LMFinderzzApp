package com.example.lmfinderzzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lmfinderzzapp.databinding.ActivityDashboardUser4Binding;
import com.example.lmfinderzzapp.databinding.ActivityDashboardUserBinding;

public class DashboardUserActivity extends AppCompatActivity {

    //view binding
    private ActivityDashboardUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //handle click back button, go to google maps screen
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardUserActivity.this, HomeUserActivity.class));
                finish();
            }
        });

        //handle click Forward button, go to google maps screen
        binding.forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardUserActivity.this, HomeUserActivity.class));
                finish();
            }
        });



    }
}