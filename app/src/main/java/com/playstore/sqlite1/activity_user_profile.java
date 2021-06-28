package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_user_profile extends AppCompatActivity {

    com.google.android.material.textfield.TextInputEditText name , email , pass;
    Button btnUpdate;
    dbContact db;
    Context context = this;
    TextView medicam,patient,consult,username,specialite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        db = new dbContact(this);
        name = (com.google.android.material.textfield.TextInputEditText) findViewById(R.id.name_profile);
        email = (com.google.android.material.textfield.TextInputEditText) findViewById(R.id.EmailProfile);
        pass = (com.google.android.material.textfield.TextInputEditText) findViewById(R.id.password_profile);
        btnUpdate = (Button) findViewById(R.id.updateProfileButton);

        //TextViews
        medicam = (TextView) findViewById(R.id.countMedicams);
        patient = (TextView) findViewById(R.id.countPatient);
        consult = (TextView) findViewById(R.id.countConsult);
        username = (TextView) findViewById(R.id.fullname_field);

        //getCount
        int nbPatient = db.countPatient();
        int nbMedicam = db.countMedicams();
        int nbConsult = db.countConsult();

        //remplir les champs
        medicam.setText(String.valueOf(nbMedicam));
        patient.setText(String.valueOf(nbPatient));
        consult.setText(String.valueOf(nbConsult));
        username.setText(db.getUser().getName());



        btnUpdate.setOnClickListener(new View.OnClickListener() {
         @Override
             public void onClick(View v) {
             if(TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(email.getText())
                     || TextUtils.isEmpty(pass.getText()) ){

                 Toast.makeText(context, "Please All the fields are required" , Toast.LENGTH_SHORT).show();
             }else{
                 String sname = name.getText().toString();
                 String semail = email.getText().toString();
                 String spass = pass.getText().toString();

                 User user = new User(sname , semail , spass );
                 db.userUpdate(user);
                 Toast.makeText(getApplicationContext() , "Profile updated succssesfullt" , Toast.LENGTH_SHORT).show();
                 finish();
             }

         }
             });

    }
}