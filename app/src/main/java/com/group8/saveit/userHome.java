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
        TextView name=findViewById(R.id.Up_Name);
        TextView phone=findViewById(R.id.textView4);
TextView email=findViewById(R.id.Up_email);
        EditText password=findViewById(R.id.Up_password);
        TextView street=findViewById(R.id.Up_street);
        TextView city=findViewById(R.id.Up_city);
        TextView zip=findViewById(R.id.Up_zip);


        if(intent!= null) {
          String value = intent.getStringExtra("customerEmail");


            dbh = new DatabaseHelper(this);

          Customer customer = dbh.getCustomerByEmail(value);
            main_Name.setText(customer.getName());
            name.setText(customer.getName());
         phone.setText(customer.getPhone());
            email.setText(customer.getEmail());
password.setText(customer.getPassword());
           street.setText(customer.getStreetName());
           city.setText(customer.getCity());
           zip.setText(customer.getPostalCode());

        }
    }
}