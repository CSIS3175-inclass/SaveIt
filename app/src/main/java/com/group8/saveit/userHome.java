package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class userHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHelper dbh;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Intent intent=getIntent();
        TextView main_Name=findViewById(R.id.txtName_P);
        if(intent!= null) {
          String value = intent.getStringExtra("customerEmail");


            //dbh = new DatabaseHelper(this);

          //  Customer customer = dbh.getCustomerByEmail(value);
            main_Name.setText(value);

        }
    }
}