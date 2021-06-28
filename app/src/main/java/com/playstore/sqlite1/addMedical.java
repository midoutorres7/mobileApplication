package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addMedical extends AppCompatActivity {
    EditText name;
    EditText dose;
    EditText clairanceMin;
    EditText clairanceMax;
    EditText biliMin;
    EditText biliMax;
    EditText tgotgaMin;
    EditText tgotgaMax;
    Button btnAddMedicam;
    dbContact db;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicam);

        name = (EditText) findViewById(R.id.name);
        dose = (EditText) findViewById(R.id.dose);
        clairanceMin = (EditText) findViewById(R.id.clairanceMin);
        clairanceMax = (EditText) findViewById(R.id.clairanceMax);
        biliMin = (EditText) findViewById(R.id.biliMin);
        biliMax = (EditText) findViewById(R.id.biliMax);
        tgotgaMin = (EditText) findViewById(R.id.tgotgaMin);
        tgotgaMax = (EditText) findViewById(R.id.tgotgaMax);

        btnAddMedicam = (Button) findViewById(R.id.btnAddMedicam);


        //instantiation of data base
        db = new dbContact(this);

        //Click on add Medicam
        btnAddMedicam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(dose.getText()) || TextUtils.isEmpty(clairanceMin.getText())
                        || TextUtils.isEmpty(clairanceMax.getText()) || TextUtils.isEmpty(biliMin.getText())
                        || TextUtils.isEmpty(biliMax.getText()) || TextUtils.isEmpty(tgotgaMin.getText()) || TextUtils.isEmpty(tgotgaMax.getText())) {
                    Toast.makeText(context , "Please All the fields are required" , Toast.LENGTH_SHORT).show();
                }else {

                    String nameM = name.getText().toString();
                    double doseM = Double.parseDouble(dose.getText().toString());
                    double clairanceMinM = Double.parseDouble(clairanceMin.getText().toString());
                    double clairanceMaxM = Double.parseDouble(clairanceMax.getText().toString());
                    double biliMinM = Double.parseDouble(biliMin.getText().toString());
                    double biliMaxM = Double.parseDouble(biliMax.getText().toString());
                    double tgotgaMinM = Double.parseDouble(tgotgaMin.getText().toString());
                    double tgotgaMaxM = Double.parseDouble(tgotgaMax.getText().toString());


                    Medicam medicam = new Medicam(nameM, doseM, clairanceMinM, clairanceMaxM, biliMinM, biliMaxM, tgotgaMinM, tgotgaMaxM);

                    db.addMedicam(medicam);

                    //Toast
                    Toast.makeText(addMedical.this, "a new Medicament added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
