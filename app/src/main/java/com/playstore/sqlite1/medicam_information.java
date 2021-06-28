package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class medicam_information extends AppCompatActivity {
    int id;
    TextView name, dose, clrMin, clrMax, biliMin, biliMax, tgoMin, tgoMax;
    Button btn;
    dbContact db;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicam_information);

        id = getIntent().getIntExtra("id" ,0 );
        db = new dbContact(this);
        Medicam medicam = db.getMedicamByID(id);

        name = (TextView) findViewById(R.id.nameInfoMedicam);
        dose = (TextView) findViewById(R.id.doseMedicamInfo);
        clrMin = (TextView) findViewById(R.id.clairanceMedicamInfoMin);
        clrMax = (TextView) findViewById(R.id.clairanceMedicamInfoMax);
        biliMin = (TextView) findViewById(R.id.biliMedicamInfoMin);
        biliMax = (TextView) findViewById(R.id.biliMedicamInfoMax);
        tgoMin = (TextView) findViewById(R.id.tgoMedicamInfoMin);
        tgoMax = (TextView) findViewById(R.id.tgoMedicamInfoMax);

        name.setText(medicam.getName());
        dose.setText(String.valueOf(medicam.getDoseComp()));
        clrMin.setText(String.valueOf(medicam.getCLAIRANCE_MIN()));
        clrMax.setText(String.valueOf(medicam.getCLAIRANCE_MAX()));
        biliMin.setText(String.valueOf(medicam.getKEY_BILI_MIN()));
        biliMax.setText(String.valueOf(medicam.getKEY_BILI_MAX()));
        tgoMin.setText(String.valueOf(medicam.getKEY_TGOTGA_MIN()));
        tgoMax.setText(String.valueOf(medicam.getKEY_TGOTGA_MAX()));



        btn = (Button) findViewById(R.id.updateHereMedicam);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(medicam_information.this , update_medicam.class);
                intent.putExtra("id" ,id);
                startActivity(intent);
            }
        });


    }
}