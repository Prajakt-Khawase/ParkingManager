package com.example.parkingmanager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class TwoWheelerActivity extends AppCompatActivity implements TwoWheelerAdapter.SlotClickListnerDownload{


    private TwoWheelerAdapter mAdapter;
    private ArrayList<String> slotList;
    private GridView gridView;
    Context context= TwoWheelerActivity.this;
    SQLiteHelper mSQLiteHelper;
    ArrayList<FourWheelerActivity.SlotModel> slotModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_wheeler_parking);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)); //status bar or the time bar at the top

        }
        mSQLiteHelper=new SQLiteHelper(this);
        slotModels=new ArrayList<>();
        findViewById(R.id.bikeslot_back).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // Cursor cursor = mSQLiteHelper.getAllSlotRecord();
        Cursor cursor = mSQLiteHelper.getSlotDetail("BIKE");
        int row =  cursor.getCount();
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            for (int i = 1; i <= row; i++) {
                FourWheelerActivity.SlotModel object = new FourWheelerActivity.SlotModel();
                int s0 = cursor.getInt(0);   //id
                int s1 = cursor.getInt(1);//name
                int s2 = cursor.getInt(2);//email
                String s3 = cursor.getString(3);//mobile

                object.id=s0;
                object.slotno=s1;
                object.status=s2;
                object.type=s3;
                cursor.moveToNext();
                slotModels.add(object);
                // Toast.makeText(TwoWheelerActivity.this, row + " " + s0 + "ID : " + s1 + " " + s2 + " " + s3 + " ", Toast.LENGTH_SHORT).show();


            }

        }
        try {
            // prepared arraylist and passed it to the Adapter class
            mAdapter = new TwoWheelerAdapter(context, slotModels);
           mAdapter.setSlotClickListnerDownload(TwoWheelerActivity.this);
            //Set custom adapter to gridview
            gridView = (GridView) findViewById(R.id.gridview);
           // gridView.setAdapter(mAdapter);
        }catch (Exception e){}
    }





}
