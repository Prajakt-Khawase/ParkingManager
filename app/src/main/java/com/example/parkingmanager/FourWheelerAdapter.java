package com.example.parkingmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    public static class ViewHolder
    {
        public ImageView imgViewFlag;
        public TextView slottext;
        public RelativeLayout slotLayout;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder view;

        if(convertView==null)
        {

            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.gridview_slot, null, false);
            view = new ViewHolder();

            view.slottext = convertView.findViewById(R.id.slot);
            view.slotLayout = convertView.findViewById(R.id.slotLayout);

            view.slottext.setText("SLOT\n"+(position+1));

            view.slotLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context, "Book Car - "+slotList.get(position), Toast.LENGTH_SHORT).show();
                }
            });

            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
