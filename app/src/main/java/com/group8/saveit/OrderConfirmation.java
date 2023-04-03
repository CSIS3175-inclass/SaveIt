package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class OrderConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        TextView orderId = findViewById(R.id.textViewOrderId);
        TextView restaurantName = findViewById(R.id.textViewRestaurantName);
        TextView address = findViewById(R.id.textViewAddress);
        TextView status = findViewById(R.id.textViewStatus);
        TextView total = findViewById(R.id.textViewTotal);

        Button backToReastaurant = findViewById(R.id.btnBackToRestaurant);

        backToReastaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //take back to restaurant page
                startActivity(new Intent(OrderConfirmation.this,RestaurantActivity.class));
            }
        });

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