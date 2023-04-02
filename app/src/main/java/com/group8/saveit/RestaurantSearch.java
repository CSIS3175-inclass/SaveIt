package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class RestaurantSearch extends AppCompatActivity {

    String arr1[] = {"res1","res2","res3","res4"};
    int arr2[] = {R.drawable.pic,R.drawable.pic,R.drawable.pic,R.drawable.pic};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
        ListView listView = findViewById(R.id.listviewRestraunt);
        RestrauntSearchAdapter adapter =new RestrauntSearchAdapter(getApplicationContext(),arr1,arr2);
        listView.setAdapter(adapter);
    }
}