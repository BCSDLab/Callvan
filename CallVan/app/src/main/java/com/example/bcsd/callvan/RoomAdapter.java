package com.example.bcsd.callvan;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EUNBEE on 2016-05-24.
 */
public class RoomAdapter extends ArrayAdapter<RoomData>{
    private Context context;
    private int layoutResourceId;
    private ArrayList<RoomData> roomData;

    public RoomAdapter(Context context, int layoutResourceId, ArrayList<RoomData> roomData) {
        super(context, layoutResourceId, roomData);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.roomData = roomData;
    }

    //list View
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //list row
        View row = convertView;
        //ViewHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.listview_lin, null);
        }

        //row layout 설정
        TextView start_tv = (TextView) row.findViewById(R.id.start_tv);
        TextView arrive_tv = (TextView) row.findViewById(R.id.arrive_tv);
        TextView premembers_tv = (TextView) row.findViewById(R.id.premembers_tv);
        TextView minmembers_tv = (TextView) row.findViewById(R.id.minmembers_tv);
        TextView time_tv = (TextView) row.findViewById(R.id.time_tv);


        start_tv.setText(roomData.get(position).getStartLocation());
        arrive_tv.setText(roomData.get(position).getArriveLocation());
        premembers_tv.setText(Integer.toString(roomData.get(position).getPresentMember()));
        minmembers_tv.setText(Integer.toString(roomData.get(position).getMinMemer()));
        time_tv.setText(roomData.get(position).getStartTime());

        return row;
    }
}
