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
        return slotList.size();
    }

    @Override
    public Object getItem(int position) {
        return slotList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
