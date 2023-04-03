package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class CurrentOrdersActivity extends AppCompatActivity {
    ArrayList<CustomerOrder> customerOrders = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_orders);
        recyclerView = findViewById(R.id.currentOrderRecycler);
        loadData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        CurrentOrdersAdapter adapter = new CurrentOrdersAdapter(customerOrders,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void loadData(){
        CustomerOrder customerOrder1 = new CustomerOrder(1,1,"pick-up","1234 Greet St");
        CustomerOrder customerOrder2 = new CustomerOrder(2,2,"delivery","5678 Miaou ave");

        FoodBundle foodBundleOne = new FoodBundle(1,"FoodBundleOne",R.drawable.imag1,10.99);
        FoodBundle foodBundleTwo = new FoodBundle(2,"FoodBundleTwo",R.drawable.image2,9.99);
        String[] items ={"item1","item2"};
        foodBundleOne.setItems(items);
        foodBundleTwo.setItems(items);

        ArrayList<FoodBundle> foodBundles = new ArrayList<>();
        foodBundles.add(foodBundleOne);
        foodBundles.add(foodBundleTwo);

        customerOrder1.setOrderedFoodBundles(foodBundles);
        customerOrder2.setOrderedFoodBundles(foodBundles);

        customerOrders.add(customerOrder1);
        customerOrders.add(customerOrder2);
    }
}