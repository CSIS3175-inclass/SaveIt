package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantSearch extends AppCompatActivity {
    ArrayList<Restaurant> restaurants=new ArrayList<>();
    DatabaseHelper databaseHelper;
//    String arr1[] = {"res1","res2","res3","res4"};
//    int arr2[] = {R.drawable.pic,R.drawable.pic,R.drawable.pic,R.drawable.pic};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
        ListView listView = findViewById(R.id.listviewRestraunt);

        // TODO: 4/4/2023 Retrieve restaurant list based on customer's address city or city clicked
        databaseHelper = new DatabaseHelper(this);
        restaurants=databaseHelper.getAllRestaurants(); //replace when done

        String[] arr1 = new String[restaurants.size()];
        int[] arr2 = new int[restaurants.size()];
        for(int i = 0; i<restaurants.size();i++){
            arr1[i]=restaurants.get(i).getName();
            arr2[i]=R.drawable.pic;
        }

        RestaurantSearchAdapter adapter =new RestaurantSearchAdapter(getApplicationContext(),arr1,arr2,restaurants);
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

            }
        });
    }
}