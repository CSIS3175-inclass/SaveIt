package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class RestaurantSearch extends AppCompatActivity {

    String arr1[] = {"res1","res2","res3","res4"};
    int arr2[] = {R.drawable.pic,R.drawable.pic,R.drawable.pic,R.drawable.pic};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
        ListView listView = findViewById(R.id.listviewRestraunt);
        RestrauntSearchAdapter adapter =new RestrauntSearchAdapter(getApplicationContext(),arr1,arr2);
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