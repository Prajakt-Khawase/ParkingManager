package com.example.parkingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button login;

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
