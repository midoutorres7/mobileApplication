package com.playstore.sqlite1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class consultation_information extends AppCompatActivity {

    int id;
    TextView editPatient, editMedicam, editDose;
    dbContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_information);

        id = getIntent().getIntExtra("id" , 0);
        //instantiasion from database
        db = new dbContact(this);
        //getContactById(id)
        Consultation consultation = db.getConsultationById(id);

        //get and set data from edit texte
        editPatient = (TextView) findViewById(R.id.nameInfoConsultation);
        editMedicam = (TextView) findViewById(R.id.medicamConsultationInfo);
        editDose = (TextView) findViewById(R.id.doseConsultationInfo);

        editPatient.setText(consultation.getContact().toString());    //+"" ==> pour le rendre un String
        editMedicam.setText(String.valueOf(consultation.getMedicam().toString()));
        editDose.setText(String.valueOf(consultation.getDoseAdmin()));

    }

    //Link of the delete delete_menu to the main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    //gestion de click sur le delete item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.iconDelete :
                showAlert();        //creer methode pour affiche alertDialogue
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //methode of showing alert dialogue
    private void showAlert() {
        //build the alert dialogue
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("confirmation")
                .setMessage("are you sure ?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete Consult
                        db.deleteConsultation(id);
                        finish();
                    }
                })

                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();  //hide the alertDialogue
                    }
                });

        //create the alert dialogue
        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }


}