package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ManageFoodBundleActivity extends AppCompatActivity {
    LinearLayout bundleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_food_bundle);

        com.group8.saveit.DatabaseHelper databaseHelper = new com.group8.saveit.DatabaseHelper(this);

        // Get list of all bundles from the database
        ArrayList<FoodBundle> bundles = databaseHelper.getAllBundles();

        // Set up recycler view with bundle adapter
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ManagerFoodBundleAdapter adapter = new ManagerFoodBundleAdapter(bundles,databaseHelper,this);
        recyclerView.setAdapter(adapter);



    }
}