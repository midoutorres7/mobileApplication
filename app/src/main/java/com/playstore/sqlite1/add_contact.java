package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_contact extends AppCompatActivity {

    EditText editName , editPhone;
    Button btnAdd;
    dbContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        btnAdd = (Button) findViewById(R.id.btnAdd);

//instansiation From DataBase
        db = new dbContact(this);

//button addContact
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                int phone = Integer.parseInt(editPhone.getText().toString());
        //create object contact for insert in database
                Contact contact = new Contact(name , phone);
        //add contact in database
                db.addContact(contact);

                //Toast
                Toast.makeText(add_contact.this , "a new contact added successfully" , Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}