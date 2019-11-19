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

import java.util.ArrayList;

public class TwoWheelerActivity extends AppCompatActivity implements TwoWheelerAdapter.SlotClickListnerDownload{


    private TwoWheelerAdapter mAdapter;
    private ArrayList<String> slotList;

    private GridView gridView;
    Context context=TwoWheelerActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_wheeler_parking);

        slotList=new ArrayList<>();
        slotList.add("SLOT 1");
        slotList.add("SLOT 2");
        slotList.add("SLOT 3");
        slotList.add("SLOT 4");
        slotList.add("SLOT 5");
        slotList.add("SLOT 6");
        slotList.add("SLOT 7");
        slotList.add("SLOT 8");
        slotList.add("SLOT 9");
        slotList.add("SLOT 10");
        slotList.add("SLOT 11");
        slotList.add("SLOT 12");
        slotList.add("SLOT 13");
        slotList.add("SLOT 14");
        slotList.add("SLOT 15");
        slotList.add("SLOT 16");
        slotList.add("SLOT 17");
        slotList.add("SLOT 18");
        slotList.add("SLOT 19");
        slotList.add("SLOT 20");

        mAdapter = new TwoWheelerAdapter(context, slotList);

        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(mAdapter);

    }
}
