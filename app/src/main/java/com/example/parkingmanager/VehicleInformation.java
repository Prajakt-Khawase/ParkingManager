package com.example.parkingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VehicleInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_information); 
    }

    public void bookSlot(View v){
        startActivity(new Intent(VehicleInformation.this, BookSlot.class));
        
    }

}
