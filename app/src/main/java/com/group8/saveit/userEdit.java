package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class userEdit extends AppCompatActivity {

    EditText name;
    EditText phoneNum;
    EditText email;
    EditText password;
    EditText street;
    EditText city;
    EditText postalCode;
    Button btnEdit;
    Button btnLogout;
    SharedPreferences sharedPreferences;
    String UEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        name = findViewById(R.id.edTxtNameM);
        phoneNum = findViewById(R.id.edTxtPhoneNumM);
        email = findViewById(R.id.edTxtEmailM);
        password = findViewById(R.id.edTxtPasswordM);
        street = findViewById(R.id.edTxtStartTime);
        city = findViewById(R.id.edTxtEndTime);
        postalCode = findViewById(R.id.edTxtPostalCode);
        btnEdit = findViewById(R.id.btnEditM);
        btnLogout = findViewById(R.id.btnLogoutM);
        sharedPreferences = getSharedPreferences("MyPreferences",MODE_PRIVATE);
        UEmail = sharedPreferences.getString("customerEmail","");
        email.setText(UEmail);

        Customer customer = dbHelper.getCustomerByEmail(UEmail);
        name.setText(customer.getName());
        phoneNum.setText(customer.getPhone());
        password.setText(customer.getPassword());
        street.setText(customer.getStreetName());
        city.setText(customer.getCity());
        postalCode.setText(customer.getPostalCode());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString();
                String newPhoneNum = phoneNum.getText().toString();
                String newPassword = password.getText().toString();
                String newStreet = street.getText().toString();
                String newCity = city.getText().toString();
                String newPostalCode = postalCode.getText().toString();

                // Update customer data in the database
                dbHelper.updateCustomer(new Customer(UEmail, newPassword, newName, newPhoneNum, newStreet, newCity, newPostalCode));

                // Show a toast message to confirm that the update was successful
                Toast.makeText(getApplicationContext(), "Customer data updated successfully", Toast.LENGTH_SHORT).show();
                email.setText(UEmail);
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Return to the login activity
                Intent intent = new Intent(userEdit.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}