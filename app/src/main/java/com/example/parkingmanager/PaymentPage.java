package com.example.parkingmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentPage extends AppCompatActivity {

    String owner,vehicleno,starttime,endtime,manager,totaltime,charge,slot,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);



        initialize();
        findViewById(R.id.payment_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }

    private void initialize() {
        TextView slotText = findViewById(R.id.payment_slot);
        TextView ownerText = findViewById(R.id.payment_owner);
        TextView vehiclenoText = findViewById(R.id.payment_vhicleno);


    }

}
