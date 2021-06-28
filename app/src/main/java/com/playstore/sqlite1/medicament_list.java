package com.playstore.sqlite1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class medicament_list extends AppCompatActivity {

    dbContact db;
    ListView medicamList;
    FloatingActionButton fab , search;
    androidx.appcompat.widget.AppCompatAutoCompleteTextView editFind;
    ArrayList<Medicam> medicams;
    MedicamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicament_list);

//instansiation de base de donnees
        db = new dbContact(this);
        medicamList = (ListView) findViewById(R.id.medicamList);
        editFind = (androidx.appcompat.widget.AppCompatAutoCompleteTextView) findViewById(R.id.helloSearch);

//Update a medicament
        medicamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medicam medicam = (Medicam) parent.getItemAtPosition(position);

                Intent intent = new Intent(medicament_list.this , medicam_information.class);
                intent.putExtra("id" , medicam.getId());
                startActivity(intent);
            }
        });


        //Float add Button
        FloatingActionButton fab = findViewById(R.id.floatBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medicament_list.this , addMedical.class);
                startActivity(intent);
            }
        });

        //Float Search Button
        search = (FloatingActionButton) findViewById(R.id.searchMedicamFloat);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = editFind.getText().toString();
                medicams = db.searchMedicam(keyWord);
                if(medicams == null) {
                    Toast.makeText(getApplicationContext() , "not exists" , Toast.LENGTH_SHORT).show();
                }else{
                    adapter = new MedicamAdapter(getApplicationContext(), R.layout.item_design2, medicams);
                    medicamList.setAdapter(adapter);
                }
            }
        });

    }



    //**************************************onResume************************************//
    @Override
    protected void onResume() {
        super.onResume();

        medicams = db.getAllMedicaments();
        adapter = new MedicamAdapter(this ,R.layout.item_design2 ,medicams);
        medicamList.setAdapter(adapter);


    }
}