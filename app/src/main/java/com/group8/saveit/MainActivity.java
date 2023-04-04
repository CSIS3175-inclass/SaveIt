package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
DatabaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbh=new DatabaseHelper(this);
        Button button = findViewById(R.id.button);
        TextView txtRegister=findViewById(R.id.registerLink);
        EditText username=findViewById(R.id.txtEmail);
        EditText password=findViewById(R.id.txtPassword);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startActivity(new Intent(MainActivity.this,Registration.class));
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if( dbh.checkUsername(username.getText().toString())){
                   Toast.makeText(MainActivity.this,"true",Toast.LENGTH_LONG).show();

               }
               else
                   Toast.makeText(MainActivity.this,"incorrect user name",Toast.LENGTH_LONG).show();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(dbh.checkPassword(username.getText().toString(),password.getText().toString()))
            {
                startActivity(new Intent(MainActivity.this, RestaurantSearch.class));
            }
            else{
                Toast.makeText(MainActivity.this,"incorrect password",Toast.LENGTH_LONG).show();
            }

            }
        });
    }
}