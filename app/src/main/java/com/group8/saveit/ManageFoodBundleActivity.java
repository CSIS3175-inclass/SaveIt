package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ManageFoodBundleActivity extends AppCompatActivity {
    LinearLayout bundleList;

    Button btnAddBundles;
    SharedPreferences sharedPreferences;
    String managerRID;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_food_bundle);

        btnAddBundles = findViewById(R.id.btnAddBundles);
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        managerRID = sharedPreferences.getString("managerRID","");
        btnAddBundles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageFoodBundleActivity.this, AddBundlesActivity.class));
            }
        });

        com.group8.saveit.DatabaseHelper databaseHelper = new com.group8.saveit.DatabaseHelper(this);

        // Get list of all bundles from the database
        ArrayList<FoodBundle> bundles = databaseHelper.getAllBundles();

        ArrayList<FoodBundle> filteredBundles = new ArrayList<>();
        for (FoodBundle foodBundle : bundles)
        {
            if(foodBundle.getRID().equals(managerRID))
            {
                filteredBundles.add(foodBundle);
            }
        }


        // Set up recycler view with bundle adapter
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ManagerFoodBundleAdapter adapter = new ManagerFoodBundleAdapter(filteredBundles,databaseHelper,this);
        recyclerView.setAdapter(adapter);

        managerMenuFragment managerMenuFragment = new managerMenuFragment();
        replaceFragment(managerMenuFragment);



    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView8,fragment);
        transaction.commit();
    }
}