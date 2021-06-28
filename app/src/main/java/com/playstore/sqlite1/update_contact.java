package com.playstore.sqlite1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class update_contact extends AppCompatActivity {


    EditText editName, editPhone, editPoids ,editAge ,  editCreatin , editBili , editTgo;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton ,radioHomme ,radioFemme ;
    Button btnUpdate;

    dbContact db;
    Context context = this;

    //GOLOBAL ID
    int id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

//receive information from itemView
        id = getIntent().getIntExtra("id" ,0 );
//instantiasion from database
        db = new dbContact(this);
//getContactById(id)
        Contact contact = db.getContactById(id);

//get and set data from edit texte
        editName = (EditText) findViewById(R.id.updateName);
        editPhone = (EditText) findViewById(R.id.updatePhone);
        radioGroup = (RadioGroup) findViewById(R.id.sexGroup);
        radioHomme = (RadioButton) findViewById(R.id.radioHomme);
        radioFemme = (RadioButton) findViewById(R.id.radioFemme);
        editAge = (EditText) findViewById(R.id.updateAge);
        editPoids = (EditText) findViewById(R.id.updatePoids);
        editCreatin = (EditText) findViewById(R.id.updatCreatine);
        editBili = (EditText) findViewById(R.id.updateBili);
        editTgo = (EditText) findViewById(R.id.updateTgo);

        editName.setText(contact.getName());    //+"" ==> pour le rendre un String
        editPhone.setText(String.valueOf(contact.getPhone()));
        editAge.setText(String.valueOf(contact.getAge()));
        editPoids.setText(String.valueOf(contact.getPoid()));
        editCreatin.setText(String.valueOf(contact.getCreatinine()));
        editBili.setText(String.valueOf(contact.getBili()));
        editTgo.setText(String.valueOf(contact.getTgoTga()));
        if(contact.getSex().equals("Homme")){
            radioHomme.setChecked(true);
        }else{
            radioFemme.setChecked(true);
        }


//click on Update Button
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRadioButton  = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                String yourSexe = selectedRadioButton.getText().toString();

                if(TextUtils.isEmpty(editName.getText()) || TextUtils.isEmpty(editPhone.getText())
                        || TextUtils.isEmpty(editPoids.getText())
                        || TextUtils.isEmpty(editCreatin.getText())
                        || TextUtils.isEmpty(editBili.getText())
                        || TextUtils.isEmpty(editTgo.getText()))
                {
                    Toast.makeText(context , "Please All the fields are required" , Toast.LENGTH_SHORT).show();
                }else {
                String name = editName.getText().toString();
                int phone = Integer.parseInt(editPhone.getText().toString());
                int age = Integer.parseInt(editAge.getText().toString());
                double poids = Double.parseDouble(editPoids.getText().toString());
                double creatine = Double.parseDouble(editCreatin.getText().toString());
                double bili = Double.parseDouble(editBili.getText().toString());
                double tgo = Double.parseDouble(editTgo.getText().toString());

                Contact currentContact = new Contact(id ,name , phone ,yourSexe , age ,poids ,creatine ,bili , tgo );

                db.contactUpdate(currentContact);
                Toast.makeText(update_contact.this, "Contact Updated With Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




//Link of the delete delete_menu to the main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu , menu);
        menu.add(0,1,Menu.NONE,"Hello item");
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
                            //delete Contact
                            db.contactDelete(id);
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