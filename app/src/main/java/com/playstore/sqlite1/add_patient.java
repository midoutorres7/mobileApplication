package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class add_patient extends AppCompatActivity {

    EditText editName , editPhone , editAge , editPoids , editCreatin , editBili , editTgo;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
    Button btnAdd;
    dbContact db;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        editAge = (EditText) findViewById(R.id.editAge);
        editPoids = (EditText) findViewById(R.id.Poids);
        editCreatin = (EditText) findViewById(R.id.creatinine);
        editBili = (EditText) findViewById(R.id.bili);
        editTgo = (EditText) findViewById(R.id.tgotga);
        btnAdd = (Button) findViewById(R.id.btnAdd);


//instansiation From DataBase
        db = new dbContact(this);

//button addContact
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRadioButton  = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                String yourSexe = selectedRadioButton.getText().toString();

                if(TextUtils.isEmpty(editName.getText()) || TextUtils.isEmpty(editPhone.getText())
                        || TextUtils.isEmpty(editPoids.getText())
                        || TextUtils.isEmpty(editCreatin.getText())
                        || TextUtils.isEmpty(editBili.getText())
                        || TextUtils.isEmpty(editTgo.getText()))
                {
                    Toast.makeText(context , "Please All the fields are required" , Toast.LENGTH_SHORT).show();
                }
                else {
                    String name = editName.getText().toString();
                    int phone = Integer.parseInt(editPhone.getText().toString());
                    //String sexe = radioSexButton.getText().toString();
                    int age = Integer.parseInt(editAge.getText().toString());
                    double poids = Double.parseDouble(editPoids.getText().toString());
                    double creatin = Double.parseDouble(editCreatin.getText().toString());
                    double bili = Double.parseDouble(editBili.getText().toString());
                    double tgo = Double.parseDouble(editTgo.getText().toString());

                    //create object contact for insert in database
                    Contact contact = new Contact(name, phone, yourSexe ,age, poids, creatin, bili, tgo);
                    //add contact in database
                    db.addContact(contact);
                    //Toast
                    Toast.makeText(add_patient.this, "a new contact added successfully "+yourSexe, Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });
    }
}