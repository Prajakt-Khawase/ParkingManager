package com.example.parkingmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class FourWheelerAdapter extends BaseAdapter {
    private ArrayList<String> slotList;

    Context context;

    public FourWheelerAdapter(Context context, ArrayList<String> slotList) {
        super();
        this.slotList = slotList;
        this.context=context;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
