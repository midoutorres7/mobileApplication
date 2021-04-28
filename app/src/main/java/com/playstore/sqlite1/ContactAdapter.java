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

public class ContactAdapter extends ArrayAdapter<Contact> {
    //declarate as Global
    Context context;
    int resource;

    //constructor
    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
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
        TextView name = (TextView) convertView.findViewById(R.id.tvName);
        TextView phone = (TextView) convertView.findViewById(R.id.tvPhone);

        //create a current object
        Contact contact = getItem(position);

        //ajuster les information dans les champs
        name.setText(contact.getName());
        phone.setText(String.valueOf(contact.getPhone()));

        return convertView;
    }
}
