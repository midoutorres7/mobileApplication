package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextClassifierEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Calculate extends AppCompatActivity {
    Bundle bundle;
    dbContact db;
    TextView doseText;
    Consultation consultation;
    Button addConsult;
    double dose_aadministrer;
    ImageView img;
    View sceen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        doseText = (TextView) findViewById(R.id.doseTxt);
        img = (ImageView) findViewById(R.id.msg);
        sceen = (View) findViewById(R.id.viewcc);
        db = new dbContact(this);

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        int patID = bundle.getInt("patientId");
        int medId = bundle.getInt("medId");

        Contact selectedContact = db.getContactById(patID);
        Medicam selectedMedicam = db.getMedicamByID(medId);

        dose_aadministrer = db.calculeDose(patID , medId);

        if(dose_aadministrer == 0) {
            doseText.setText("Ce Medicament est contre indique");
            img.setImageResource(R.drawable.alarm);
            sceen.setBackgroundColor(Color.parseColor("#ff8080"));
        }
        else{
            img.setImageResource(R.drawable.checked);
            sceen.setBackgroundColor(Color.parseColor("#84e184"));
            doseText.setText("La Dose Administrer est de: "+dose_aadministrer+" mg/min");
            consultation = new Consultation(selectedContact , selectedMedicam , dose_aadministrer);
        }


        addConsult = (Button) findViewById(R.id.addConsult);
        addConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dose_aadministrer == 0) {
                    Toast.makeText(getApplicationContext() , "you can't add this consultation" , Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addConsultation(consultation);
                    Toast.makeText(getApplicationContext() , "Consultation added" , Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });


    }
}