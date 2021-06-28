package com.playstore.sqlite1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConsultAdapter extends ArrayAdapter<Consultation> {
    //declarate as Global
    Context context;
    int resource;

    //constructor
    public ConsultAdapter(@NonNull Context context, int resource, @NonNull List<Consultation> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }



    //Override of getView methode
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //ajuster le design au items
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        //get text views
        TextView name = (TextView) convertView.findViewById(R.id.ttt1);
        TextView phone = (TextView) convertView.findViewById(R.id.ttt2);

        //create a current object
        Consultation consultation = getItem(position);

        name.setText(consultation.getContact().toString());
        phone.setText(consultation.getMedicam().toString());

        return convertView;
    }
}
