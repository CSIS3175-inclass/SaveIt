package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper=new DatabaseHelper(this);
        loadDB(); //load database from assets/ sql files

        Button button = findViewById(R.id.button);
        TextView txtRegister=findViewById(R.id.registerLink);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startActivity(new Intent(MainActivity.this,Registration.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,RestaurantActivity.class));
                //take to Restaurant search activity
                startActivity(new Intent(MainActivity.this,RestaurantSearch.class));
            }
        });
    }
    public void loadDB(){
        try {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            db.execSQL("DELETE FROM Restaurant_table;"); //clear the table first
            db.execSQL("DELETE FROM Bundles_table;"); //clear the table first
            db.execSQL("DELETE FROM User_table;"); //clear the table first
            db.execSQL("DELETE FROM Manager_table;"); //clear the table first

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