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


                //take to Restaurant search activity
                Intent intent = new Intent(MainActivity.this,RestaurantSearch.class);
                intent.putExtra("customerEmail",customerEmail);
                startActivity(intent);
            }
           else if(databaseHelper.checkPassword(username.getText().toString(),password.getText().toString())=="manager")
                {
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
                    editor.apply();
                    Log.d("MainActivity", "RID: " + managerRID);
                    Log.d("MainActivity", "SharedPreference RID: " + sharedPreferences.getString("managerRID", ""));
                    startActivity(new Intent(MainActivity.this, HomeManagerActivity.class));
                }else {
                Toast.makeText(MainActivity.this,"incorrect password",Toast.LENGTH_LONG).show();
            }

            }
        });
    }
    public void loadDB(){
        try {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            //clear the table first
            db.execSQL("DELETE FROM Restaurant_table;");
            db.execSQL("DELETE FROM Bundles_table;");
            db.execSQL("DELETE FROM User_table;");
            db.execSQL("DELETE FROM Manager_table;");
            db.execSQL("DELETE FROM Order_table");
            db.execSQL("DELETE FROM Order_Bundle_table");

            //read from file and load tables
            //restaurant table
            InputStream inputStream = this.getAssets().open("Restaurant_table.sql");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine())!=null){
                db.execSQL(line);
            }
            inputStream.close();

            //bundle table
            inputStream = this.getAssets().open("Bundles_table.sql");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while((line= reader.readLine())!=null)
            {
                db.execSQL(line);
            }
            inputStream.close();

            //user table
            inputStream = this.getAssets().open("User_table.sql");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while((line= reader.readLine())!=null)
            {
                db.execSQL(line);
            }
            inputStream.close();

            //manager table
            inputStream = this.getAssets().open("Manager_table.sql");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while((line= reader.readLine())!=null)
            {
                db.execSQL(line);
            }
            inputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}