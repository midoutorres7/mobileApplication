package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class consultation_list extends AppCompatActivity {
    dbContact db;
    ListView consultList;
    FloatingActionButton search;
    androidx.appcompat.widget.AppCompatAutoCompleteTextView editFind;
    ArrayList<Consultation> consultations;
    ConsultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_list);

        //instansiation de base de donnees
        db = new dbContact(this);
        consultList = (ListView) findViewById(R.id.consutlList);
        editFind = (androidx.appcompat.widget.AppCompatAutoCompleteTextView) findViewById(R.id.helloConsult);


        search = (FloatingActionButton) findViewById(R.id.searchConsultFloat);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = editFind.getText().toString();
                consultations = db.searchConsultation(keyWord);
                if(consultations == null) {
                    Toast.makeText(getApplicationContext() , "not exists" , Toast.LENGTH_SHORT).show();
                }else{
                    adapter = new ConsultAdapter(getApplicationContext(), R.layout.item_design3, consultations);
                    consultList.setAdapter(adapter);
                }
            }
        });



//Update a medicament
        consultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Consultation consultation = (Consultation) parent.getItemAtPosition(position);

                Intent intent = new Intent(consultation_list.this , consultation_information.class);
                intent.putExtra("id" , consultation.getId());
                startActivity(intent);
            }
        });


    }


    //**************************************onResume************************************//
    @Override
    protected void onResume() {
        super.onResume();

        consultations = db.getAllConsultations();
        adapter = new ConsultAdapter(this ,R.layout.item_design3 ,consultations);
        consultList.setAdapter(adapter);

    }

}