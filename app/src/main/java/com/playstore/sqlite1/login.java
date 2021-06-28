package com.playstore.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
Button btnLogin;
EditText email , password;
dbContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.inputEmail);
        password = (EditText) findViewById(R.id.inputPassword);
        db = new dbContact(this);
        User user = db.getUser();

        Toast.makeText(this ,user.getName() , Toast.LENGTH_SHORT).show();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals(user.getEmail()) && password.getText().toString().equals(user.getPassword())){
                    Intent intent = new Intent(login.this , FirstPage.class);
                    startActivity(intent);
                    finish();
                }else{

                }

            }
        });

    }



}