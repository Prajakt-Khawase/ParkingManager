package com.example.parkingmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Context context=HomeActivity.this;
    Button button1,button2,button3,button4;
    ImageView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)); //status bar or the time bar at the top
        }

        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        logout=findViewById(R.id.home_logout);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        logout.setOnClickListener(this);
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        appCloseAlert();
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button1:
                Intent intentButton1 = new Intent(context, FourWheelerActivity.class);
                startActivity(intentButton1);
                break;

            case R.id.button2:
                Intent intentButton2 = new Intent(context, TwoWheelerActivity.class);
                startActivity(intentButton2);
                break;

            case R.id.button3:

                break;

            case R.id.button4:
                break;

            case R.id.home_logout:

                boolean status= SharedPrefrenceUtilities.getSPbooleanValue(HomeActivity.this,SharedPrefrenceUtilities.spIsLoggedin);
                logoutAlert();

                break;
        }
    }
    public void logoutAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Are you sure want to LOGOUT?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //handle logic here

                SharedPrefrenceUtilities.setSPboolean(HomeActivity.this,SharedPrefrenceUtilities.spIsLoggedin,false);
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //nothing here
            }
        });
        builder.show();
    }

    public void appCloseAlert(){

    }
    }

