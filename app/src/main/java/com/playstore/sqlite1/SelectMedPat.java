package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class SelectMedPat extends AppCompatActivity {
    Spinner spnPatient , spnMed;
    Button btnTest;
    dbContact db;
    Bundle bundle;
    Contact selectedContact;
    Medicam medicam;
    ImageButton listOfConsult;

    //ProgressBar
    private ProgressBar mProgressBar;
    private TextView mLoadingText;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_med_pat);

        spnPatient = (Spinner) findViewById(R.id.spinPatient);
        spnMed = (Spinner) findViewById(R.id.spinMed);

        db = new dbContact(this);

        bundle = new Bundle();

////////////////////////////////Patient///////////////////////////////////////////////////////
        ArrayList<Contact> values = db.getAllContacts();
        ArrayAdapter<Contact> adapter = new ArrayAdapter<>(this , android.R.layout.simple_spinner_dropdown_item , values);
        //SpinnerAdapterPatient adapter = new SpinnerAdapterPatient(this , R.layout.item_design_spinner , values);
        spnPatient.setAdapter(adapter);
        spnPatient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedContact = (Contact) parent.getItemAtPosition(position);
                bundle.putInt("patientId" , selectedContact.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

/////////////////////////////Medicaments/////////////////////////////////////////////////////
        ArrayList<Medicam> medicams = db.getAllMedicaments();
        ArrayAdapter<Medicam> MedAdapter = new ArrayAdapter<>(this , android.R.layout.simple_spinner_dropdown_item , medicams);
        //MedicamAdapter MedAdapter = new MedicamAdapter(this , R.layout.item_design2 , medicams);
        spnMed.setAdapter(MedAdapter);
        spnMed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medicam = (Medicam) parent.getItemAtPosition(position);
                bundle.putInt("medId" , medicam.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        listOfConsult = (ImageButton) findViewById(R.id.listOfConsult);
        listOfConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectMedPat.this , consultation_list.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {

        btnTest = (Button) findViewById(R.id.test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PROGRESSBAR
                mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
                mLoadingText = (TextView) findViewById(R.id.LoadingCompleteTextView);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mProgressStatus < 100){
                            mProgressStatus++;
                            android.os.SystemClock.sleep(20);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(mProgressStatus);
                                }
                            });
                        }
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mLoadingText.setVisibility(View.VISIBLE);
                            }
                        });

                        //INTEEEEEEEEEENT
                        Intent intent = new Intent(SelectMedPat.this , Calculate.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                }).start();


            }
        });


        super.onResume();
    }

    public void showData(Medicam med){
        String name = med.getName();
        Toast.makeText(this , "medicament="+name , Toast.LENGTH_SHORT).show();

    }
}
