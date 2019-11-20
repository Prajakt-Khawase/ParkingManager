package com.example.parkingmanager;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FourWheelerActivity extends AppCompatActivity {
    private FourWheelerAdapter mAdapter;
    private ArrayList<String> slotList;
    private GridView gridView;
    Context context=FourWheelerActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_wheeler);
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
        slotList.add("SLOT 21");
        slotList.add("SLOT 22");
        slotList.add("SLOT 23");
        slotList.add("SLOT 24");
        slotList.add("SLOT 25");

        mAdapter = new FourWheelerAdapter(context, slotList);
        gridView = (GridView) findViewById(R.id.GridView);
        gridView.setAdapter(mAdapter);

    }

    public static class SlotModel {
        public int id;
        public int slotno;
        public int status;
        public String type;
    }
}
