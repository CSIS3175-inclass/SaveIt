package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {
    String customerEmail;
    DatabaseHelper databaseHelper;
    ArrayList<CustomerOrder> orders ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        Intent intent = getIntent();
        if(intent!=null){
            customerEmail = intent.getStringExtra("customerEmail");
            databaseHelper = new DatabaseHelper(this);
            orders=databaseHelper.getAllOrderByUser(customerEmail);
            Log.d("test", "orders "+orders);
            RecyclerView ordersHistoryRecyclerView = findViewById(R.id.recyclerviewFavoriteRestaurants);

            OrderHistoryAdapter orderHistoryAdapter =new OrderHistoryAdapter(getApplicationContext(),orders);
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