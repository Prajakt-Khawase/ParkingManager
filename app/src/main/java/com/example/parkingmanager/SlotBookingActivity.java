package com.example.parkingmanager;

import android.content.Context;
import android.content.Intent;
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

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault());
        dateTime = sdf.format(new Date());
        dateTimeEt.setText(""+dateTime);


        //Toast.makeText(this, ""+dateTime, Toast.LENGTH_SHORT).show();

        // get slot from previous class intent
        try {
            slotno = getIntent().getIntExtra("slotNo", 0);
            type = getIntent().getStringExtra("type");
            slotid=getIntent().getIntExtra("slotid", 0);
            manager = SharedPrefrenceUtilities.getSPstringValue(context, SharedPrefrenceUtilities.spFirstName);
        }catch (Exception e){}

    }

    private void initialize() {

        dateTimeEt = findViewById(R.id.dateTime);
        ownerEt = findViewById(R.id.ownerName);
        mobileEt = findViewById(R.id.mobileno);
        vehicleEt = findViewById(R.id.vehicleno);
        back = findViewById(R.id.booking_back);
        book = findViewById(R.id.book_button);
        emailEt = findViewById(R.id.email);


    }

    private void validation() {

        owner = ownerEt.getText().toString().toLowerCase().trim();
        vehicleno = vehicleEt.getText().toString().trim().toLowerCase();
        mobile = mobileEt.getText().toString().trim();
        email=emailEt.getText().toString().toLowerCase();

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
        } else if (mobile.isEmpty()) {
            mobileEt.setError("Enter mobile number");
            mobileEt.requestFocus();
        }
        else if (mobile.length()!= 10) {
            mobileEt.setError("Enter 10 digit valid mobile number");
            mobileEt.requestFocus();
        }
        else if (vehicleno.isEmpty()) {
            vehicleEt.setError("Enter vehicle number");
            vehicleEt.requestFocus();
        } else {
            //Toast.makeText(context, "all set", Toast.LENGTH_SHORT).show();

            mSQLiteHelper.insertBookingRecord(slotno, owner, email,mobile,vehicleno,dateTime,"NA",manager,type,true);
            mSQLiteHelper.updateBikeSlotDetail(Integer.toString(slotid), Integer.toString(slotno),true,type);


            String address="";
            if(type.equals("CAR"))
            {
                address= "https://prajakt.000webhostapp.com/parking/car/CarSlot"+slotno+".jpg";
            }
            else if(type.equals("BIKE"))
            {
                address= "https://prajakt.000webhostapp.com/parking/bike/twowheeler"+slotno+".png";

            }
            String bookingMsg="Hello, "+owner+"" +
                    "\n\nParking slot "+slotno+ " is booked for your vehicle "+ vehicleno+"."
                    + "\n\nStart time is "+dateTime+".\nPlease follow parking slot route using following " +
                    "address and park your vehicle on alloted slot.\n\n"+address
                    +"\n\nThank you for choosing our Parking Service.\n\n\n\nRegards,\nMid_Team5 Parking Service";

            Intent intent=new Intent(Intent.ACTION_SEND);
            String[] recipients={email};
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Parking Booking");
            intent.putExtra(Intent.EXTRA_TEXT,bookingMsg);
            intent.putExtra(Intent.EXTRA_CC,"khawse.prajaktadm@gmail.com");
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));

            finish();
        }

    }

    //Email check
    public static boolean isValidEmail(CharSequence email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
