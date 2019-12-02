package com.example.parkingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ParkingDetailActivity extends AppCompatActivity {


    TextView bookedCarslot,bookedBikeSlot,remainingCarSlot,remainingBikeSlot;

    SQLiteHelper sqLiteHelper;
    int carCount=0,bikeCount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_detail);
        sqLiteHelper=new SQLiteHelper(this);



        findViewById(R.id.parkingdetail_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();    }
        });

        initialize();

        carCount=getCount("CAR");
        bikeCount=getCount("BIKE");

        int remCarCount = 25-carCount;
        int remBikeCount = 25-bikeCount;



        bookedCarslot.setText(carCount+" Slot");
        bookedBikeSlot.setText(bikeCount+" Slot");

        remainingBikeSlot.setText(remBikeCount+" Slot");
        remainingCarSlot.setText(remCarCount+" Slot");




    }

    private void initialize()
    {
        bookedBikeSlot=findViewById(R.id.pd_bookedBikeSlot);
        bookedCarslot=findViewById(R.id.pd_bookedCarSlot);
        remainingBikeSlot=findViewById(R.id.pd_remaingBikeSlot);
        remainingCarSlot=findViewById(R.id.pd_remaingCarSlot);

    }



    private int getCount(String type)
    {
        int count=0;
        // Cursor cursor = mSQLiteHelper.getAllSlotRecord();
        Cursor cursorCar = sqLiteHelper.getSlotDetail(type);
        int row =  cursorCar.getCount();
        if (cursorCar != null && cursorCar.getCount() != 0) {
            cursorCar.moveToFirst();
            for (int i = 1; i <= row; i++) {
                int s2 = cursorCar.getInt(2);
                if(s2==1)
                {
                    count++;
                }
                cursorCar.moveToNext();


            }}

        return  count;
    }
}
