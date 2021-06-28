package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class patient_informations extends AppCompatActivity {
    int id;
    TextView editName, editPhone, editSex, editPoids ,editAge ,  editCreatin , editBili , editTgo ;
    Button btnupdate;
    dbContact db;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_informations);

        id = getIntent().getIntExtra("id" ,0 );
        //instantiasion from database
        db = new dbContact(this);
        //getContactById(id)
        Contact contact = db.getContactById(id);

        //get and set data from edit texte
        editName = (TextView) findViewById(R.id.nameInfo);
        editPhone = (TextView) findViewById(R.id.phoneInfo);
        editSex = (TextView) findViewById(R.id.genderInfo);
        editAge = (TextView) findViewById(R.id.ageInfo);
        editPoids = (TextView) findViewById(R.id.weightInfo);
        editCreatin = (TextView) findViewById(R.id.creatinInfo);
        editBili = (TextView) findViewById(R.id.biliInfo);
        editTgo = (TextView) findViewById(R.id.tgoInfo);

        editName.setText(contact.getName());    //+"" ==> pour le rendre un String
        editPhone.setText(String.valueOf(contact.getPhone()));
        editSex.setText(String.valueOf(contact.getSex()));
        editAge.setText(String.valueOf(contact.getAge()));
        editPoids.setText(String.valueOf(contact.getPoid()));
        editCreatin.setText(String.valueOf(contact.getCreatinine()));
        editBili.setText(String.valueOf(contact.getBili()));
        editTgo.setText(String.valueOf(contact.getTgoTga()));

        btnupdate = (Button) findViewById(R.id.updateHere);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(patient_informations.this , update_contact.class);
                intent.putExtra("id" ,id);
                startActivity(intent);
            }
        });




    }





}