package com.playstore.sqlite1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class update_medicam extends AppCompatActivity {
    int id;
    EditText name, dose, clrMin, clrMax, biliMin, biliMax, tgoMin, tgoMax;
    Button updateBtn;
    dbContact db;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicam);

        id = getIntent().getIntExtra("id", 0);
        db = new dbContact(this);
        Medicam medicam = db.getMedicamByID(id);

        name = (EditText) findViewById(R.id.updateName);
        dose = (EditText) findViewById(R.id.updateDose);
        clrMin = (EditText) findViewById(R.id.updateClrMin);
        clrMax = (EditText) findViewById(R.id.updateClrMax);
        biliMin = (EditText) findViewById(R.id.updateBiliMin);
        biliMax = (EditText) findViewById(R.id.updateBiliMax);
        tgoMin = (EditText) findViewById(R.id.updateTgoMin);
        tgoMax = (EditText) findViewById(R.id.updateTgoMax);

        name.setText(medicam.getName());
        dose.setText(String.valueOf(medicam.getDoseComp()));
        clrMin.setText(String.valueOf(medicam.getCLAIRANCE_MIN()));
        clrMax.setText(String.valueOf(medicam.getCLAIRANCE_MAX()));
        biliMin.setText(String.valueOf(medicam.getKEY_BILI_MIN()));
        biliMax.setText(String.valueOf(medicam.getKEY_BILI_MAX()));
        tgoMin.setText(String.valueOf(medicam.getKEY_TGOTGA_MIN()));
        tgoMax.setText(String.valueOf(medicam.getKEY_TGOTGA_MAX()));

        updateBtn = (Button) findViewById(R.id.updateMed);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(dose.getText()) || TextUtils.isEmpty(clrMin.getText())
                        || TextUtils.isEmpty(clrMax.getText()) || TextUtils.isEmpty(biliMin.getText())
                        || TextUtils.isEmpty(biliMax.getText()) || TextUtils.isEmpty(tgoMin.getText()) || TextUtils.isEmpty(tgoMax.getText())) {
                    Toast.makeText(context , "Please All the fields are required" , Toast.LENGTH_SHORT).show();
                }else {
                    String nameM = name.getText().toString();
                    double doseM = Double.parseDouble(dose.getText().toString());
                    double cmn = Double.parseDouble(clrMin.getText().toString());
                    double cmx = Double.parseDouble(clrMax.getText().toString());
                    double bilmn = Double.parseDouble(biliMin.getText().toString());
                    double bilmx = Double.parseDouble(biliMax.getText().toString());
                    double tgmn = Double.parseDouble(tgoMin.getText().toString());
                    double tgmx = Double.parseDouble(tgoMax.getText().toString());

                    Medicam medicam = new Medicam(id, nameM, doseM, cmn, cmx, bilmn, bilmx, tgmn, tgmx);

                    db.medicamUpdate(medicam);

                    Toast.makeText(update_medicam.this, "Medicament Updated With Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    //Link of the delete delete_menu to the main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //gestion de click sur le delete item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iconDelete:
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
                        //delete Contact
                        db.deleteMedicam(id);
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

