package com.example.parkingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
    }

    public void invokeLogin(View v){
        startActivity(new Intent(MainActivity.this, VehicleInformation.class));
        this.finish();

    }

    public void signUp(View v){
        startActivity(new Intent(MainActivity.this, SignupPage.class));
        this.finish();
    }

}
