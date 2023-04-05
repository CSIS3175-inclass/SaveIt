package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeManagerActivity extends AppCompatActivity {
    int restaurantId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_manager);
        ImageButton manageFoodBundles = findViewById(R.id.iBtnManage);
        ImageButton test = findViewById(R.id.ibtnOrders);

        Intent intent = getIntent();
        if(intent!=null)
        {
            restaurantId = intent.getIntExtra("restaurantId",0);
            manageFoodBundles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeManagerActivity.this, AddBundlesActivity.class));
                }
            });

            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                startActivity(new Intent(HomeManagerActivity.this, ManageFoodBundleActivity.class));
                    Intent ordersIntent = new Intent(HomeManagerActivity.this, CurrentOrdersActivity.class);
                    ordersIntent.putExtra("restaurantId",restaurantId);
                    startActivity(intent);
                }
            });
        }


    }
}