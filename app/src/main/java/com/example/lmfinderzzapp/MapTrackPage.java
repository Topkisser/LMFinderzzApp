package com.example.lmfinderzzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MapTrackPage extends AppCompatActivity {

    //Initialize variables
    EditText etSource, etDestination;
    Button btTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_track_page);


        //Assign variables
        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.trackBtn);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get values from edit texts
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                //Validate inputs
                if(sSource.equals("") && sDestination.equals("")){
                    //If both values are blank
                    Toast.makeText(getApplicationContext(),
                            "Please Enter both locations",Toast.LENGTH_SHORT).show();
                }else{
                    //Both values filled
                    //Display track data
                    DisplayTrack(sSource,sDestination);
                }
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
        //Check if device has maps installed if not redirect to the play store
        try{
            //if google maps is installed
            //Initialize Uri
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource + "/"
            + sDestination);
            //Initialize intent with a action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //set packages
            intent.setPackage("com.google.android.apps.maps");
            //set flags
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //start the activity
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            //When google maps is not installed
            //Initialize uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            //Initialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //start the activity
            startActivity(intent);

        }

    }
}