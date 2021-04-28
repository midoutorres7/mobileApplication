package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    dbContact db;
    Button btnAdd;
    ListView contactList;

    TextView nametxt;
    TextView phonetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Buuton btnAdd
        btnAdd = (Button) findViewById(R.id.btnAdd);

        //create a listview
        contactList = (ListView) findViewById(R.id.contactList);

        //databse instansiation
        db = new dbContact(this);


     //////////////////DELETE//////////////////ADD///////////////UPDATE/////////////////////////////

     //click on item
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = (Contact) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this , update_contact.class);
                intent.putExtra("id" ,selectedContact.getId());
                startActivity(intent);
            }
        });



    //click on add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , add_contact.class);
                startActivity(intent);
            }
        });

    }






    /////////////////////////////////////la methode onResume////////////////////////////////////////
    @Override
    protected void onResume() {
        super.onResume();

//create an arrayList
        ArrayList<Contact> contacts = db.getAllContacts();

//create Objects Adapter
        ContactAdapter adapter = new ContactAdapter(this,R.layout.item_design ,contacts) ;

//ajust Adapter to ListView
        contactList.setAdapter(adapter);

    }
}