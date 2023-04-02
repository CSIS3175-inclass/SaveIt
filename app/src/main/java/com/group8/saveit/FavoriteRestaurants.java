package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

public class FavoriteRestaurants extends AppCompatActivity {

    String arr1[] = {"res1","res2","res3","res4"};
    int arr2[] = {R.drawable.pic,R.drawable.pic,R.drawable.pic,R.drawable.pic};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_restaurants);
        //ListView listView = findViewById(R.id.listviewFavoriteRestaurant);
        RecyclerView recyclerView = findViewById(R.id.recyclerviewFavoriteRestaurant);
        FavoriteRestaurantAdapter favoriteRestaurantAdapter =new FavoriteRestaurantAdapter(getApplicationContext(),arr1,arr2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(favoriteRestaurantAdapter);
    }
}