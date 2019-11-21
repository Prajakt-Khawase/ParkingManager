package com.example.parkingmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TwoWheelerAdapter extends BaseAdapter
{
    private ArrayList<FourWheelerActivity.SlotModel> slotModels;
    SlotClickListnerDownload slotClickListnerDownload;

    Context context;

    public TwoWheelerAdapter(Context context, ArrayList<FourWheelerActivity.SlotModel> slotModels) {
        super();
        this.slotModels = slotModels;
        this.context=context;
    }


    public interface SlotClickListnerDownload{
        public void  onDownloadClickDeleteListner(FourWheelerActivity.SlotModel slotModel);


    }


    public void setSlotClickListnerDownload(SlotClickListnerDownload slotClickListnerDownload) {

        this.slotClickListnerDownload=slotClickListnerDownload;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return slotModels.size();
    }

    @Override
    public FourWheelerActivity.SlotModel getItem(int position) {
        // TODO Auto-generated method stub
        return slotModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        ViewHolder view;
        final FourWheelerActivity.SlotModel slotModel = getItem(position);
        if(convertView==null)
        {

            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.gridview_slot, null, false);
            view = new ViewHolder();

            view.slottext = convertView.findViewById(R.id.slot);
            view.slotLayout = convertView.findViewById(R.id.slotLayout);

            view.slottext.setText("SLOT\n"+(position+1));
            if(slotModel.status == 1)
            {
                view.slottext.setBackgroundResource(R.drawable.green);
            }else
            {
                view.slottext.setBackgroundResource(R.drawable.white);

            }
//


            view.slotLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(slotModels!=null)
                    {
                        slotClickListnerDownload.onDownloadClickDeleteListner(slotModel);
                        //Toast.makeText(context, "slot- "+slotModel.id+"  "+slotModel.status+" "+slotModel.slotno+" "+slotModel.type, Toast.LENGTH_SHORT).show();


                    }

                }
            });

            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
            view.slottext.setText("SLOT\n"+(position+1));
            if(slotModel.status == 1)
            {
                view.slottext.setBackgroundResource(R.drawable.green);
            }else
            {
                view.slottext.setBackgroundResource(R.drawable.white);

            }
            view.slotLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(slotModels!=null)
                    {
                        slotClickListnerDownload.onDownloadClickDeleteListner(slotModel);
                        //Toast.makeText(context, "slot- "+slotModel.id+"  "+slotModel.status+" "+slotModel.slotno+" "+slotModel.type, Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }

        return convertView;
    }

}
