package com.example.dellpc.riderapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell pc on 03-Jan-18.
 */

public class AdapterReceiveOrder extends ArrayAdapter<ClassRecOrder> {
    public AdapterReceiveOrder(Context context, int resource, List<ClassRecOrder> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null){
        convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.custom_rec_order,parent,false);
    }
    TextView name = (TextView) convertView.findViewById(R.id.name);
    TextView time = (TextView) convertView.findViewById(R.id.time);

    ClassRecOrder classOrder = getItem(position);

        name.setText(classOrder.getConsigneeName());
        time.setText(classOrder.getDatetime());
    return convertView;
}
}
