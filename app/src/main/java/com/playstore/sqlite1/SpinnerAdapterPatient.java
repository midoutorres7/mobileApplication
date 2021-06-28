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

public class SpinnerAdapterPatient extends ArrayAdapter<Contact> {
    Context context;
    int resource;

    public SpinnerAdapterPatient(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        TextView txtv = (TextView) convertView.findViewById(R.id.spinnerItem);
        Contact currentContact = getItem(position);
        txtv.setText(currentContact.getName());

        return convertView;
    }
}
