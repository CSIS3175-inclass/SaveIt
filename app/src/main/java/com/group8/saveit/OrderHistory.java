package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {

    String customerEmail;
    DatabaseHelper databaseHelper;

    ArrayList<Order> orders ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        Intent intent = getIntent();
        customerEmail = intent.getStringExtra("customerEmail");
        databaseHelper = new DatabaseHelper(this);
        orders=databaseHelper.getAllOrderByUser("abc");
        RecyclerView ordersHistoryRecyclerView = findViewById(R.id.recyclerviewFavoriteRestaurants);
        OrderHistoryAdapter orderHistoryAdapter =new OrderHistoryAdapter(getApplicationContext(),orders);
        ordersHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersHistoryRecyclerView.setAdapter(orderHistoryAdapter);

        ImageButton home = findViewById(R.id.home_icon);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderHistory.this,RestaurantSearch.class));
            }
        });

        ImageButton history = findViewById(R.id.history_icon);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(OrderHistory.this,OrderHistory.class));
            }
        });

        ImageButton favoriteRestaurants = findViewById(R.id.favorite_icon);

        favoriteRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderHistory.this,FavoriteRestaurants.class));
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