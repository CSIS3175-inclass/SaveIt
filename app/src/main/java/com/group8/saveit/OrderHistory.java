package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class OrderHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        RecyclerView ordersHistoryRecyclerView = findViewById(R.id.recyclerviewFavoriteRestaurants);
        OrderHistoryAdapter orderHistoryAdapter =new OrderHistoryAdapter(getApplicationContext());
        ordersHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersHistoryRecyclerView.setAdapter(orderHistoryAdapter);

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