package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class managerRegister extends AppCompatActivity {
DatabaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_register);
        dbh=new DatabaseHelper(this);
        EditText name=findViewById(R.id.txtName);
        EditText email=findViewById(R.id.txtEmail_r);
        EditText password=findViewById(R.id.txtPassword_r);
        EditText phone=findViewById(R.id.txtPhone);
        EditText street=findViewById(R.id.txtStreet);
        EditText city=findViewById(R.id.txtcity);
        EditText zipcode=findViewById(R.id.txtzipcode);
        Button btnRegister=findViewById(R.id.btnRegister);
        EditText sTime=findViewById(R.id.startTime);
        EditText eTime=findViewById(R.id.endTime);
EditText rName=findViewById(R.id.restName);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;


            @Override
            public void onClick(View view) {

                isInserted = dbh.addDataManager(
                        name.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        phone.getText().toString(),


                        street.getText().toString(),
                        city.getText().toString(),
                        zipcode.getText().toString(),
                        rName.getText().toString(),
                        sTime.getText().toString(),
                        eTime.getText().toString());


                if(isInserted){
                    Toast.makeText(managerRegister.this,"data added",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(managerRegister.this,MainActivity.class));
                }
                else{
                    Toast.makeText(managerRegister.this,"data not added",Toast.LENGTH_LONG).show();
                }
            }
        });;

    }
}