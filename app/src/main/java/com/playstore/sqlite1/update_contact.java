package com.playstore.sqlite1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class update_contact extends AppCompatActivity {

    dbContact db;
    Button btnUpdate;
    EditText editName, editPhone;

    //GOLOBAL
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
        editName.setText(contact.getName());    //+"" ==> pour le rendre un String
        editPhone.setText(String.valueOf(contact.getPhone()));


//click on Update Button
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                int phone = Integer.parseInt(editPhone.getText().toString());
                Contact currentContact = new Contact(id,name,phone);
                db.contactUpdate(currentContact);
                Toast.makeText(update_contact.this, "Contact Updates With Success", Toast.LENGTH_SHORT).show();
            }
        });
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
                            //delete Contact
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