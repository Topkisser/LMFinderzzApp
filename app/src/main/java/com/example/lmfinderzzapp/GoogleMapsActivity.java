package com.example.lmfinderzzapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.lmfinderzzapp.databinding.ActivityGoogleMapsBinding;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityGoogleMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGoogleMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //handle click moraineLakeIV, go to special page
        binding.schoolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GoogleMapsActivity.this, HomeUserActivity.class));
                finish();
            }
        });
        //handle click search image button, go to special page
        binding.glassThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GoogleMapsActivity.this, MapTrackPage.class));
                finish();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Vereeniging and move the camera
        LatLng vrega = new LatLng(-26.673010, 27.928770);
        mMap.addMarker(new MarkerOptions().position(vrega).title("Marker in Vereeniging"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vrega));
    }
}