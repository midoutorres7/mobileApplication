package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fist_page);

        RelativeLayout profil = (RelativeLayout) findViewById(R.id.selectProfil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstPage.this , activity_user_profile.class);
                startActivity(intent);
            }
        });

        RelativeLayout btnSelect = (RelativeLayout) findViewById(R.id.selectMed);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstPage.this , SelectMedPat.class);
                startActivity(intent);
            }
        });

        RelativeLayout btnOut = (RelativeLayout) findViewById(R.id.patientList);
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstPage.this , patientList.class);
                startActivity(intent);
            }
        });

        RelativeLayout btnMedList = (RelativeLayout) findViewById((R.id.medList));
        btnMedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstPage.this , medicament_list.class);
                startActivity(intent);
            }
        });

        RelativeLayout btnLogOut = (RelativeLayout) findViewById((R.id.logOut));
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstPage.this , login.class);
                startActivity(intent);
            }
        });

    }
}