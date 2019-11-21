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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)); //status bar or the time bar at the top
        }
        mSQLiteHelper = new SQLiteHelper(this);
        initialize();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validation();
            }
        });

        manager= SharedPrefrenceUtilities.getSPstringValue(context,SharedPrefrenceUtilities.spFirstName);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        dateTime = sdf.format(new Date());
        dateTimeEt.setText(""+dateTime);


        //Toast.makeText(this, ""+dateTime, Toast.LENGTH_SHORT).show();

        // get slot from previous class intent
        try {
            slotno = getIntent().getIntExtra("slotNo", 0);
            type = getIntent().getStringExtra("type");
            slotid=getIntent().getIntExtra("slotid", 0);
           // manager = SharedPrefrenceUtilities.getSPstringValue(context, SharedPrefrenceUtilities.spFirstName);
        }catch (Exception e){}

    }

    private void initialize() {




    }

    private void validation() {



        if (owner.isEmpty()) {
            ownerEt.setError("Enter vehicle owner name");
            ownerEt.requestFocus();
        } else if(email.isEmpty())
        {
            emailEt.setError("Enter email");
            emailEt.requestFocus();
        } else if(!isValidEmail(email))
        {
            emailEt.setError("Enter valid email");
            emailEt.requestFocus();
        } if (mobile.isEmpty()) {
            mobileEt.setError("Enter mobile number");
            mobileEt.requestFocus();
        } else if (vehicleno.isEmpty()) {
            vehicleEt.setError("Enter vehicle number");
            vehicleEt.requestFocus();
        } else {
            //Toast.makeText(context, "all set", Toast.LENGTH_SHORT).show();

            mSQLiteHelper.insertBookingRecord(slotno, owner, mobile,email,vehicleno,dateTime,"NA",manager,type,true);
            mSQLiteHelper.updateBikeSlotDetail(Integer.toString(slotid), Integer.toString(slotno),true,type);

            finish();

        }

    }

    //Email check
    public static boolean isValidEmail(CharSequence email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

