package com.playstore.sqlite1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class patientList extends AppCompatActivity {
    dbContact db;
    ListView contactList;
    FloatingActionButton fab , search;
    ContactAdapter adapter;
    androidx.appcompat.widget.AppCompatAutoCompleteTextView editSearch;
    ArrayList<Contact> contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
       /* initView();
        loadData();*/


        //create a listview
        contactList = (ListView) findViewById(R.id.contactList);
        //databse
        db = new dbContact(this);
        editSearch = (androidx.appcompat.widget.AppCompatAutoCompleteTextView) findViewById(R.id.editSearchPatient);


        //////////////////DELETE//////////////////////////UPDATE/////////////////////////////

        //click on item
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = (Contact) parent.getItemAtPosition(position);

                Intent intent = new Intent(patientList.this, patient_informations.class);
                intent.putExtra("id", selectedContact.getId());
                startActivity(intent);
            }
        });


        //Float add Button
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(patientList.this, add_patient.class);
                startActivity(intent);
            }
        });

        //float search btn
        search = (FloatingActionButton) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = editSearch.getText().toString();
                contacts = db.search(keyWord);
                if (contacts == null) {
                    Toast.makeText(getApplicationContext(), "not exists", Toast.LENGTH_SHORT).show();
                } else {
                    adapter = new ContactAdapter(getApplicationContext(), R.layout.item_design, contacts);
                    contactList.setAdapter(adapter);

                }
            }
        });

    }
    /////////////////////////////////////ON RESUME////////////////////////////////////////

    @Override
    protected void onResume() {
        super.onResume();

//create an arrayList
        contacts = db.getAllContacts();

//create Objects Adapter
        adapter = new ContactAdapter(this,R.layout.item_design ,contacts) ;

//ajust Adapter to ListView
        contactList.setAdapter(adapter);

    }


}
