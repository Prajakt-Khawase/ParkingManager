package com.example.parkingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CustomerLogout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_logout);
    }
        public void onlogout(View v){
            startActivity(new Intent(CustomerLogout.this, MainActivity.class));
            this.finish();

        }


}
