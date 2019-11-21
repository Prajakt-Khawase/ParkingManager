package com.example.parkingmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SlotBookingActivity extends AppCompatActivity {

    Context context;
    EditText ownerEt, mobileEt, vehicleEt, dateTimeEt,emailEt;
    Button book;
    ImageView back;
    String owner, type, mobile, vehicleno, dateTime, manager,email;
    int slotno,slotid;
    SQLiteHelper mSQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_booking);
        context = SlotBookingActivity.this;
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)); //status bar or the time bar at the top
       // }
        //mSQLiteHelper = new SQLiteHelper(this);
       // initialize();

        //back.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
               // finish();
           // }
       // });

       // book.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {

                //validation();
            //}
       // });

      //  manager= SharedPrefrenceUtilities.getSPstringValue(context,SharedPrefrenceUtilities.spFirstName);


    }

    private void initialize() {

    }

    private void validation(){

    }
    }
