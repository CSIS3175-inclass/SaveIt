package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
DatabaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dbh=new DatabaseHelper(this);
        TextView link=findViewById(R.id.registermanagerLink);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this,managerRegister.class));





            }
        });
        EditText name=findViewById(R.id.txtName);
        EditText email=findViewById(R.id.txtEmail_r);
        EditText password=findViewById(R.id.txtPassword_r);
        EditText phone=findViewById(R.id.txtPhone);
        EditText street=findViewById(R.id.txtStreet);
        EditText city=findViewById(R.id.txtcity);
        EditText zipcode=findViewById(R.id.txtzipcode);
        Button btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View view) {


                isInserted = dbh.addData(
                        name.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        phone.getText().toString(),


                        street.getText().toString(),
                        city.getText().toString(),
                        zipcode.getText().toString());

                if(isInserted){
                    Toast.makeText(Registration.this,"data added",Toast.LENGTH_LONG).show();
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    phone.setText("");
                }
                else{
                    Toast.makeText(Registration.this,"data not added",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}