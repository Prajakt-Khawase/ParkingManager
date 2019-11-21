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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
            public void onClick(View v) {
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


            // Set custom adapter to gridview
            gridView = (GridView) findViewById(R.id.gridview);
            gridView.setAdapter(mAdapter);
        }catch (Exception e){}



    }

    @Override
    public void onDownloadClickDeleteListner(FourWheelerActivity.SlotModel slotModel) {
        try {

            //  Toast.makeText(context, "Book 2-Wheeler slot- " + slotModel.slotno, Toast.LENGTH_SHORT).show();
            if(slotModel.status==1)
            {
                bookedSlotAlert(slotModel);
            }
            else {
                Intent intent = new Intent(context, SlotBookingActivity.class);
                intent.putExtra("slotNo", slotModel.slotno);
                intent.putExtra("type", "BIKE");
                intent.putExtra("slotid", slotModel.id);
                startActivity(intent);
                finish();
            }
        }
        catch(Exception e){}


    }



    //slot already booked activity
    public void bookedSlotAlert(final FourWheelerActivity.SlotModel slotModel){
        AlertDialog.Builder builder = new AlertDialog.Builder(TwoWheelerActivity.this);
        builder.setTitle("Slot Already Booked...")
                .setMessage("Do you want to release SLOT?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(DialogInterface dialog, int id) {
                //handle yes logic here

                String slotno = Integer.toString(slotModel.slotno);
                String type = slotModel.type;
                String status = "1";

                try {


                    Cursor cursor = mSQLiteHelper.getSlotDetail(slotno, type, status);
                    int row = cursor.getCount();
                    int columnCount=cursor.getColumnCount();
                    Toast.makeText(context, "" +cursor.getColumnCount(), Toast.LENGTH_SHORT).show();
                    if (cursor != null && cursor.getCount() != 0) {
                        cursor.moveToFirst();
                        for (int i = 1; i <= row; i++) {
                            //SlotModel object = new SlotModel();

                            String book_id = cursor.getString(0);
                            String slot = cursor.getString(1);
                            String owner = cursor.getString(2);
                            String mobile = cursor.getString(3);
                            String vehicle = cursor.getString(4);
                            String intime = cursor.getString(5);
                            String outtime = cursor.getString(6);
                            String manager = cursor.getString(7);


                            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                            outtime = sdf.format(new Date());
                            int charge = calculateCharge(intime, outtime);

                            mSQLiteHelper.releaseSlotDetail(book_id, false, outtime, charge);
                            mSQLiteHelper.updateBikeSlotDetail(Integer.toString(slotModel.id), Integer.toString(slotModel.slotno), false, slotModel.type);

                            Intent intent = new Intent(TwoWheelerActivity.this, PaymentPage.class);
                            intent.putExtra("owner", owner);
                            intent.putExtra("vehicleno", vehicle);
                            intent.putExtra("starttime", intime);
                            intent.putExtra("endtime", outtime);
                            intent.putExtra("manager", manager);
                            intent.putExtra("totaltime", totaltime);
                            intent.putExtra("charge", "$"+charge);
                            intent.putExtra("slot", slot);

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
    //@Override
    public  void onResume()
    {
        super.onResume();
         startActivity(getIntent());
    }


     String totaltime="";
     @RequiresApi(api = Build.VERSION_CODES.N)
     public int calculateCharge(String sd, String ed) {
       // int charge =0;
       // try {
           // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
          //  Date startDate = simpleDateFormat.parse(String.valueOf(sd));
         //   Date endDate = simpleDateFormat.parse(String.valueOf(ed));

          //  long difference = endDate.getTime() - startDate.getTime();
           // if (difference < 0) {
              //  Date dateMax = simpleDateFormat.parse("24:00");
              //  Date dateMin = simpleDateFormat.parse("00:00");
                //difference = (dateMax.getTime() - startDate.getTime()) + (endDate.getTime() - dateMin.getTime());
          //  }
           // int days = (int) (difference / (1000 * 60 * 60 * 24));
          //  int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
          //  int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
            // Log.e("log_tag", "days  :" + days + "  Hours: " + hours + ", Mins: " + min);


          //  totaltime = days+" days "+ hours +" hr "+min+" min";



          //  int dayCharge= days * 5;
          //  int hourCharge;
          //  if(hours<=8)
          //  {
                //hourCharge = 2;
           // }else if(hours>8 && hours<=16)
            //{
              //  hourCharge =  4;

            //}else
            //{
              //  hourCharge=5;
            //}

           // charge = hourCharge + dayCharge;


       // }catch(Exception e){}

       // return charge;

    }

}
