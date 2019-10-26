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
    }
}
