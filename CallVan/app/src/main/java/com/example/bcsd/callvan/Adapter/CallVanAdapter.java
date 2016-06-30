package com.example.bcsd.callvan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.example.bcsd.callvan.Data.CallVanData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HYERIM on 2016-06-03.
 */
//콜밴 adapter
public class CallVanAdapter extends ArrayAdapter<CallVanData> {
    final String TAG = "CallVanAdapter";

    private Context context;
    private int resource;
    private ArrayList<CallVanData> callVanData;

    public CallVanAdapter(Context context, int resource, ArrayList<CallVanData> callVanData) {
        super(context, resource, callVanData);
    }

    public void add(CallVanData van){ callVanData.add(van);}

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CallVanData getItem(int position) {
        return callVanData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
