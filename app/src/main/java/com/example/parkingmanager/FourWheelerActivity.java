package com.example.parkingmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.Toast;
import android.icu.util.Calendar;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FourWheelerActivity extends AppCompatActivity implements FourWheelerAdapter.SlotClickListnerDownload {


    private FourWheelerAdapter mAdapter;
    private ArrayList<String> slotList;

    ArrayList<SlotModel> slotModels;
    SQLiteHelper mSQLiteHelper;


    private GridView gridView;
    Context context= FourWheelerActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_wheeler);


        mSQLiteHelper=new SQLiteHelper(this);

        slotModels=new ArrayList<>();
        Cursor cursor = mSQLiteHelper.getSlotDetail("CAR");     // to get the slot detail

        int row =  cursor.getCount();
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            for (int i = 1; i <= row; i++) {
                SlotModel object = new SlotModel();
                int s0 = cursor.getInt(0);   //id
                int s1 = cursor.getInt(1);
                int s2 = cursor.getInt(2);
                String s3 = cursor.getString(3);
                object.id=s0;
                object.slotno=s1;
                object.status=s2;
                object.type=s3;
                cursor.moveToNext();

                slotModels.add(object);

                // Toast.makeText(FourWheelerActivity.this, row + " " + s0 + "ID : " + s1 + " " + s2 + " " + s3 + " ", Toast.LENGTH_SHORT).show();
            }

        }



        findViewById(R.id.carslot_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        // prepared arraylist and passed it to the Adapter class
        mAdapter = new FourWheelerAdapter(context, slotModels);
        mAdapter.setSlotClickListnerDownload(FourWheelerActivity.this);


        // Set custom adapter to gridview
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(mAdapter);



    }

    @Override
    public void onDownloadClickDeleteListner(SlotModel slotModel) {
        try {

            //  Toast.makeText(context, "Book 2-Wheeler slot- " + slotModel.slotno, Toast.LENGTH_SHORT).show();

            if(slotModel.status==1)
            {
                bookedSlotAlert(slotModel);
            }
            else {
                Intent intent = new Intent(context, SlotBookingActivity.class);
                intent.putExtra("slotNo", slotModel.slotno);
                intent.putExtra("type", "CAR");
                intent.putExtra("slotid", slotModel.id);

                startActivity(intent);
                finish();
            }
        }
        catch(Exception e){}

    }

    //slot already booked activity
    public void bookedSlotAlert(final SlotModel slotModel){
        AlertDialog.Builder builder = new AlertDialog.Builder(FourWheelerActivity.this);
        builder.setTitle("Slot Already Booked...")
                .setMessage("Do you want to release SLOT?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(DialogInterface dialog, int id) {
                //handle yes logic here
                // Toast.makeText(context, "Release slot logic here", Toast.LENGTH_SHORT).show();


                String slotno = Integer.toString(slotModel.slotno);
                String type = slotModel.type;
                String status = "1";


                try {


                    Cursor cursor = mSQLiteHelper.getSlotDetail(slotno, type, status);
                    int row = cursor.getCount();
                    int columnCount=cursor.getColumnCount();
                    //  Toast.makeText(context, "" +cursor.getColumnCount(), Toast.LENGTH_SHORT).show();
                    if (cursor != null && cursor.getCount() != 0) {
                        cursor.moveToFirst();
                        for (int i = 1; i <= row; i++) {
                            //SlotModel object = new SlotModel();

                            String book_id = cursor.getString(0);
                            String slot = cursor.getString(1);

                            String owner = cursor.getString(2);
                            String email = cursor.getString(3);
                            String vehicle = cursor.getString(4);
                            String intime = cursor.getString(5);
                            String outtime = cursor.getString(6);

                            String manager = cursor.getString(7);
                            //String type = cursor.getString(8);
                            // String status = cursor.getString(9);
//                            String s10 = cursor.getString(10);
//                            String s11 = cursor.getString(11);
//

                            //  Toast.makeText(context, s6+" "+s9, Toast.LENGTH_SHORT).show();

                            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault());
                            outtime = sdf.format(new Date());
                            int charge = calculateCharge(intime, outtime);

                            mSQLiteHelper.releaseSlotDetail(book_id, false, outtime, charge);
                            mSQLiteHelper.updateBikeSlotDetail(Integer.toString(slotModel.id), Integer.toString(slotModel.slotno), false, slotModel.type);

                            Intent intent = new Intent(FourWheelerActivity.this, PaymentPage.class);
                            intent.putExtra("owner", owner);
                            intent.putExtra("vehicleno", vehicle);
                            intent.putExtra("starttime", intime);
                            intent.putExtra("endtime", outtime);
                            intent.putExtra("manager", manager);
                            intent.putExtra("totaltime", totaltime);
                            intent.putExtra("charge", "$"+charge);
                            intent.putExtra("slot", slot);
                            intent.putExtra("email", email);


                            startActivity(intent);
                            finish();


                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //nothing here
            }
        });
        builder.show();
    }


    //to refresh screen when load
    @Override
    public  void onResume()
    {
        super.onResume();
        //startActivity(getIntent());
    }
    //calculate charge

    String totaltime;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int calculateCharge(String sd, String ed) {
        int charge =0;
        try {
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault());

            Date startDate = sdf.parse(String.valueOf(sd));
            Date endDate = sdf.parse(String.valueOf(ed));


            long difference = endDate.getTime() - startDate.getTime();
            if (difference < 0) {
                Date dateMax = sdf.parse("24:00");
                Date dateMin = sdf.parse("00:00");
                difference = (dateMax.getTime() - startDate.getTime()) + (endDate.getTime() - dateMin.getTime());
            }
            int days = (int) (difference / (1000 * 60 * 60 * 24));
            int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
            int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
            // Log.e("log_tag", "days  :" + days + "  Hours: " + hours + ", Mins: " + min);


            totaltime = days+" days "+ hours +" hr "+min+" min";


            int dayCharge= days * 10;
            int hourCharge;
            if(hours<=8)
            {
                hourCharge = 4;
            }else if(hours>8 && hours<=16)
            {
                hourCharge =  8;

            }else
            {
                hourCharge=10;
            }

            charge = hourCharge + dayCharge;


        }catch(Exception e){}

        return charge;

    }

    public static class SlotModel {
        public int id;
        public int slotno;
        public int status;
        public String type;
    }
}
