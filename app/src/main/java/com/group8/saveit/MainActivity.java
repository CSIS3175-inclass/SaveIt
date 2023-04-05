package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    String customerEmail; //keep track of customer's id throughout the activities to add new Order
    int restaurantId;
    String managerEmail;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper=new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("MyPreferences",MODE_PRIVATE);
        loadDB(); //load database from assets/ sql files

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
               if( databaseHelper.checkUsername(username.getText().toString())){
                   Toast.makeText(MainActivity.this,"true",Toast.LENGTH_LONG).show();

               }
               else
                   Toast.makeText(MainActivity.this,"incorrect user name",Toast.LENGTH_LONG).show();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                

            if(databaseHelper.checkPassword(username.getText().toString(),password.getText().toString())=="user")
            {
                customerEmail=username.getText().toString(); // TODO: 4/4/2023 replace with customer email from login credential
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("customerEmail", customerEmail);
                editor.apply();

                //take to Restaurant search activity
                Intent intent = new Intent(MainActivity.this,RestaurantSearch.class);
                intent.putExtra("customerEmail",customerEmail);
                startActivity(intent);
            }
           else if(databaseHelper.checkPassword(username.getText().toString(),password.getText().toString())=="manager")
                {
                    Intent homeManagerIntent = new Intent(MainActivity.this, HomeManagerActivity.class);
                    restaurantId = databaseHelper.getRestaurantIdByManager(username.getText().toString());
                    homeManagerIntent.putExtra("restaurantId",restaurantId);
                    
                    Cursor c =databaseHelper.getRIDByEmail(username.getText().toString());

                    StringBuilder managerRID = new StringBuilder();
                    if(c.getCount() >0)
                    {
                    while(c.moveToNext())
                    {
                        managerRID.append(c.getString(0));
                    }
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("managerRID", managerRID.toString());
                    editor.putString("RID", managerRID.toString());
                    editor.putString("MEmail", username.getText().toString());
                    editor.apply();
                    Log.d("MainActivity", "RID: " + managerRID);
                    Log.d("MainActivity", "SharedPreference RID: " + sharedPreferences.getString("managerRID", ""));
                    startActivity(homeManagerIntent);
                }else {
                Toast.makeText(MainActivity.this,"incorrect password",Toast.LENGTH_LONG).show();
            }

            }
        });
    }

}