package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantSearch extends AppCompatActivity {
    ArrayList<Restaurant> restaurants=new ArrayList<>();
    DatabaseHelper databaseHelper;
    String customerEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
        Intent intent = getIntent();

        if(intent!=null){
            //get email from Login or sing in activity
            customerEmail = intent.getStringExtra("customerEmail");
            ListView listView = findViewById(R.id.listviewRestraunt);

            // TODO: 4/4/2023 Retrieve restaurant list based on customer's address city or city clicked
            databaseHelper = new DatabaseHelper(this);
            restaurants=databaseHelper.getAllRestaurants(); //replace when done

            //populate data required for RestaurantSearchAdapter
            String[] arr1 = new String[restaurants.size()];
            int[] arr2 = new int[restaurants.size()];
            for(int i = 0; i<restaurants.size();i++){
                arr1[i]=restaurants.get(i).getName();
                arr2[i]=R.drawable.pic;
            }

            RestaurantSearchAdapter adapter =new RestaurantSearchAdapter(getApplicationContext(),arr1,arr2,restaurants,customerEmail);
            listView.setAdapter(adapter);

            ImageButton home = findViewById(R.id.home_icon);

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            ImageButton history = findViewById(R.id.history_icon);

            history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            ImageButton favoriteRestaurants = findViewById(R.id.favorite_icon);

            favoriteRestaurants.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            ImageButton profile = findViewById(R.id.profile_icon);

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RestaurantSearch.this,userHome.class);
                    intent.putExtra("customerEmail",customerEmail);
                    startActivity(intent);


                }
            });
        }

    }
}