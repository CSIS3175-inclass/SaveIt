package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RestaurantActivity extends AppCompatActivity{
    Button orderBtn;
    ArrayList<FoodBundle> selectedFoodBundles= new ArrayList<FoodBundle>();
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        Intent intent = getIntent();
        databaseHelper = new DatabaseHelper(this);

        if(intent!=null){

            //get restaurantId from RestaurantSearch activity
            int restaurantId = intent.getIntExtra("restaurantId",0);

//            FoodBundleFragment foodBundleFragment=new FoodBundleFragment();
            Log.i("test"," restaurant activity restaurantId "+ restaurantId+" database "+databaseHelper);
            FoodBundleFragment foodBundleFragment=new FoodBundleFragment(databaseHelper,restaurantId);

            //pass restaurantId and databasehelper to foodBundleFragment
//            foodBundleFragment.setDatabaseHelper(databaseHelper);
            replaceFragment(foodBundleFragment); //replace BundlesContainerView with FoodBundleFragment

            orderBtn=findViewById(R.id.order);
            orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //get all restaurant's foodbundles
                    ArrayList<FoodBundle> foodBundles= foodBundleFragment.getFoodBundles();
                    //add the ones that are checked to selectedFoodBundle
                    for(int i =0; i<foodBundles.size();i++){
                        FoodBundle foodBundle=foodBundles.get(i);
                        if(foodBundle.isChecked()){
                            selectedFoodBundles.add(foodBundle);
                        }
                    }

                    //send selectedFoodBundle to OrderSummaryActivity
                    Intent intent = new Intent(RestaurantActivity.this,OrderSummaryActivity.class);
                    intent.putExtra("selectedFoodBundles",selectedFoodBundles);
                    startActivity(intent);
                }
            });
        }


    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.BundlesContainerView,fragment);
        transaction.commit();
    }

}