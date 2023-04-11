package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeManagerActivity extends AppCompatActivity {
    int restaurantId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_manager);
        ImageButton manageFoodBundles = findViewById(R.id.iBtnManage);
        ImageButton currentOrder = findViewById(R.id.ibtnOrders);


        Intent intent = getIntent();
        if(intent!=null)
        {
            restaurantId = intent.getIntExtra("restaurantId",0);
                    manageFoodBundles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeManagerActivity.this, ManageFoodBundleActivity.class));
            }
            });

        currentOrder.setOnClickListener(new View.OnClickListener() {
             @Override
                public void onClick(View v) {
//                startActivity(new Intent(HomeManagerActivity.this, ManageFoodBundleActivity.class));
                    Intent ordersIntent = new Intent(HomeManagerActivity.this, CurrentOrdersActivity.class);
                    ordersIntent.putExtra("restaurantId",restaurantId);
                    startActivity(ordersIntent);
                }
            });

            
            managerMenuFragment managerMenuFragment = new managerMenuFragment(restaurantId);
            replaceFragment(managerMenuFragment);
        }



    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView2,fragment);
        transaction.commit();
    }
}