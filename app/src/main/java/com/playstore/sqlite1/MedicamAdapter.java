package com.playstore.sqlite1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MedicamAdapter extends ArrayAdapter<Medicam> {
    //declarate as Global
    Context context;
    int resource;

    public MedicamAdapter(@NonNull Context context, int resource, @NonNull List<Medicam> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        //get text views
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName2);
        TextView tvDose = (TextView) convertView.findViewById(R.id.tvdose);


        //create a current object
        Medicam medicam = getItem(position);

        //ajuster les information dans les champs
        tvName.setText(medicam.getName());
        tvDose.setText(String.valueOf(medicam.getDoseComp()));


        return convertView;
    }
}