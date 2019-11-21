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
}
