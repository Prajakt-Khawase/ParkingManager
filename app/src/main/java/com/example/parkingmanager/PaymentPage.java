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

        findViewById(R.id.SendMail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });


    }

    private void initialize() {
        TextView slotText = findViewById(R.id.payment_slot);
        TextView ownerText = findViewById(R.id.payment_owner);
        TextView vehiclenoText = findViewById(R.id.payment_vhicleno);
        TextView starttimeText = findViewById(R.id.payment_start);
        TextView endtimeText = findViewById(R.id.payment_endTime);
        TextView managerText = findViewById(R.id.payment_manager);
        TextView totaltimeText = findViewById(R.id.payment_totaltime);
        TextView chargeText = findViewById(R.id.payment_totalcharge);
        try {


            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                slot = bundle.getString("slot");
                owner = bundle.getString("owner");
                vehicleno = bundle.getString("vehicleno");
                starttime = bundle.getString("starttime");
                endtime = bundle.getString("endtime");
                manager = bundle.getString("manager");
                totaltime = bundle.getString("totaltime");
                charge = bundle.getString("charge");
            }
                slotText.setText(slot);
                ownerText.setText(owner);
                vehiclenoText.setText(vehicleno);
                starttimeText.setText(starttime);
                endtimeText.setText(endtime);
                managerText.setText(manager);
                totaltimeText.setText(totaltime);
                chargeText.setText(charge);



            }catch (Exception e){
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    private void sendMail()
    {


        String paymentMsg="Hello, "+owner+"\n\n\n\nYour total time duration for "+vehicleno+" parking is "+totaltime+"  \nThe total Parking charge is "+charge+"\n\nThanks for choosing our Parking Service.\n\n\nThank You!\n\n\nRegards,\nPrajakt Parking Service";

        Intent intent=new Intent(Intent.ACTION_SEND);
        String[] recipients={"pk@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Parking Payment");
        intent.putExtra(Intent.EXTRA_TEXT,paymentMsg);
        intent.putExtra(Intent.EXTRA_CC,"khawse.prajaktadm@gmail.com");
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(Intent.createChooser(intent, "Send mail"));
        finish();
            }
    }

}
