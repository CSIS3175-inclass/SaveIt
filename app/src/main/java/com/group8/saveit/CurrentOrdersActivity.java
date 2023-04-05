package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class CurrentOrdersActivity extends AppCompatActivity {
    ArrayList<CustomerOrder> customerOrders = new ArrayList<>();
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    int restaurantId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_orders);
        Intent intent = getIntent();
        if(intent!=null){
            restaurantId = intent.getIntExtra("restaurantId",restaurantId);

            databaseHelper = new DatabaseHelper(this);
            recyclerView = findViewById(R.id.currentOrderRecycler);

            loadData();

            if(!customerOrders.isEmpty()){
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setHasFixedSize(true);
                CurrentOrdersAdapter adapter = new CurrentOrdersAdapter(customerOrders,this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        }

    }

    public void loadData(){
        customerOrders = databaseHelper.getOrdersByRid(restaurantId);
    }
}