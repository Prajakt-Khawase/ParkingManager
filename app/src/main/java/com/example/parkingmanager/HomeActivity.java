package com.example.parkingmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;

    Button button1, button2, button3, button4;
    ImageView logout;

    SQLiteHelper mSQLiteHelper;

    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = HomeActivity.this;


        mSQLiteHelper = new SQLiteHelper(this);
        initialize();


    }

    private void initialize() {

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        logout = findViewById(R.id.home_logout);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        logout.setOnClickListener(this);

        //create slot table first time
        createSlotTable();


    }

    private void createSlotTable() {

        boolean slotCreation = SharedPrefrenceUtilities.getSPbooleanValue(context, SharedPrefrenceUtilities.spSlotTableCreationStatus);

        if (!slotCreation) {
            try {
                for (int i = 1; i <= 25; i++) {

                    String slotType = "BIKE";
                    mSQLiteHelper.insertSlotRecord(i, false, slotType);
                }

                for (int i = 1; i <= 25; i++) {
                    String slotType = "CAR";
                    mSQLiteHelper.insertSlotRecord(i, false, slotType);
                }
                SharedPrefrenceUtilities.setSPboolean(HomeActivity.this, SharedPrefrenceUtilities.spSlotTableCreationStatus, true);

            } catch (Exception e) {
                Toast.makeText(context, "" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        // Cursor cursor = mSQLiteHelper.getSlotRecord();
        //  int rowCount =  cursor.getCount();
        // Toast.makeText(context, slotCreation+" "+row, Toast.LENGTH_SHORT).show();


    }

    ///logout
    public void logoutAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Are you sure want to LOGOUT?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //handle logic here

                SharedPrefrenceUtilities.setSPboolean(HomeActivity.this, SharedPrefrenceUtilities.spIsLoggedin, false);
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


    //close app
    public void appCloseAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Close App?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //handle yes logic here
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


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
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
                startActivity(new Intent(HomeActivity.this, RateCardActivity.class));

                break;

            case R.id.button4:
                startActivity(new Intent(HomeActivity.this, ParkingDetailActivity.class));
                break;

            case R.id.home_logout:

                //SharedPrefrenceUtilities.getSPbooleanValue(HomeActivity.this, SharedPrefrenceUtilities.spIsLoggedin);


                logoutAlert();

                break;
        }


    }
}
