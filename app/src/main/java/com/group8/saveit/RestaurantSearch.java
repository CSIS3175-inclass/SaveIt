package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantSearch extends AppCompatActivity {
    ArrayList<Restaurant> restaurants=new ArrayList<>();
    DatabaseHelper databaseHelper;
    String customerEmail;
    RestaurantSearchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
        Intent intent = getIntent();

        if(intent!=null){
            //get email from Login or sing in activity
            customerEmail = intent.getStringExtra("customerEmail");
            ListView listView = findViewById(R.id.listviewRestraunt);
            Button btn1 = findViewById(R.id.btnCity1);
            Button btn2 = findViewById(R.id.btnCity2);
            Button btn3 = findViewById(R.id.btnCity3);
            Button btn4 = findViewById(R.id.btnCity4);
            EditText cityName = findViewById(R.id.editTextTextCitySearch);
            Button btnSearch = findViewById(R.id.btnCitySearch);

            // TODO: 4/4/2023 Retrieve restaurant list based on customer's address city or city clicked
            databaseHelper = new DatabaseHelper(this);
            restaurants=databaseHelper.getAllRestaurants(); //replace when done


            adapter =new RestaurantSearchAdapter(getApplicationContext(),restaurants,customerEmail);
            listView.setAdapter(adapter);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    restaurants = databaseHelper.getAllRestaurantsByCity("Surrey");
                    adapter =new RestaurantSearchAdapter(getApplicationContext(),restaurants,customerEmail);
                    listView.setAdapter(adapter);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    restaurants = databaseHelper.getAllRestaurantsByCity("New Westminster");
                    adapter =new RestaurantSearchAdapter(getApplicationContext(),restaurants,customerEmail);
                    listView.setAdapter(adapter);
                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    restaurants = databaseHelper.getAllRestaurantsByCity("Richmond");
                    adapter =new RestaurantSearchAdapter(getApplicationContext(),restaurants,customerEmail);
                    listView.setAdapter(adapter);
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    restaurants = databaseHelper.getAllRestaurantsByCity("Burnaby");
                    adapter =new RestaurantSearchAdapter(getApplicationContext(),restaurants,customerEmail);
                    listView.setAdapter(adapter);
                }
            });

            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!cityName.getText().toString().equals("")){
                        restaurants = databaseHelper.getAllRestaurantsByCity(cityName.getText().toString());
                        adapter =new RestaurantSearchAdapter(getApplicationContext(),restaurants,customerEmail);
                        listView.setAdapter(adapter);
                    }

                }
            });

            ImageButton home = findViewById(R.id.home_icon);

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //startActivity(new Intent(RestaurantSearch.this,RestaurantSearch.class));
                }
            });

            ImageButton history = findViewById(R.id.history_icon);

            history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(RestaurantSearch.this,OrderHistory.class));
                }
            });

            ImageButton favoriteRestaurants = findViewById(R.id.favorite_icon);

            favoriteRestaurants.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(RestaurantSearch.this,FavoriteRestaurants.class));
                }
            });

            ImageButton profile = findViewById(R.id.profile_icon);

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

    }
}