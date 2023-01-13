package com.example.lmfinderzzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.lmfinderzzapp.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    //view binding
    private ActivityRegisterBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        //setup progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        //handle click, go back
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                onBackPressed();
            }
        });


        //handle click, begin register
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateDate();
            }
        });

    }

    private String name = "", email = "", password = "", cPassword = "";

    private void validateDate() {
        /*Before creating account, lets do data validation*/

        //get data
        name = binding.nameEt.getText().toString().trim();
        email = binding.emailEt.getText().toString().trim();
        password = binding.passwordEt.getText().toString().trim();
        cPassword = binding.cPasswordEt.getText().toString().trim();

        //validate data
        if(TextUtils.isEmpty(name)){

            Toast.makeText(this, "Enter your name...", Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            Toast.makeText(this, "Invalid email pattern...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){

            Toast.makeText(this, "Enter a password...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cPassword)){

            Toast.makeText(this, "Confirm Password...", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(cPassword)){

            Toast.makeText(this, "Password does not match...", Toast.LENGTH_SHORT).show();
        }
        else{

            createUserAccount();
        }
    }

    private void createUserAccount() {

        //show progress
        progressDialog.setMessage("Creating account...");
        progressDialog.show();

        //create user in firebase auth
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //account creation success also add data into firebase realtime database
                        updateUserInfo();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        //account creation failed
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void updateUserInfo() {
        progressDialog.setMessage("Saving user data...");

        //Timestamp
        long timestamp = System.currentTimeMillis();

        //get current user uid now user is registered
        String uid = firebaseAuth.getUid();

        //Setup data to add to database
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("email", email);
        hashMap.put("name", name);
        hashMap.put("profileImage", "");//will do later
        hashMap.put("userType", "user");//admin or user
        hashMap.put("timestamp", timestamp);

        //set data to database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //data added to database successfully
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Account created...", Toast.LENGTH_SHORT).show();
                        //user account created take them to the dashboard
                        startActivity(new Intent(RegisterActivity.this, HomeUserActivity.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        //data failed to upload to database
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }
}