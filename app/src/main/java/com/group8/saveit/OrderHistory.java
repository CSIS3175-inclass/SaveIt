package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class OrderHistory extends AppCompatActivity {
    String customerEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        Intent intent = getIntent();
        if(intent!=null){
            customerEmail = intent.getStringExtra("customerEmail");
            RecyclerView ordersHistoryRecyclerView = findViewById(R.id.recyclerviewFavoriteRestaurants);
            OrderHistoryAdapter orderHistoryAdapter =new OrderHistoryAdapter(getApplicationContext());
            ordersHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            ordersHistoryRecyclerView.setAdapter(orderHistoryAdapter);

            UserMenuFragment userMenuFragment = new UserMenuFragment(customerEmail);
            replaceFragment(userMenuFragment);
        }

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.navigationContainerView3,fragment);
        transaction.commit();
    }
}