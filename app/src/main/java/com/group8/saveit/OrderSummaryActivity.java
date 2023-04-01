package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class OrderSummaryActivity extends AppCompatActivity implements OrderSummaryAdapter.OnDataChangedListener{
    RecyclerView recyclerView;
    double total;
    TextView totalPrice;
    ArrayList<FoodBundle> foodBundles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        total=0;
        Intent intent=getIntent();
        totalPrice = findViewById(R.id.totalPrice);
        if(intent!=null){
            ArrayList<FoodBundle> selectedFoodBundles=(ArrayList<FoodBundle>) intent.getSerializableExtra("selectedFoodBundles");
            foodBundles=selectedFoodBundles;
            Log.i("test",selectedFoodBundles.get(0).getBundleName());
            recyclerView=findViewById(R.id.orderRecyclerView);
//            recyclerView.setLayoutManager(new GridLayoutManager(this,1));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            OrderSummaryAdapter adapter=new OrderSummaryAdapter(this,selectedFoodBundles,totalPrice);
            adapter.setOnDataChangedListener(this);
            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onDataChanged() {
        //update order's total price
        if(foodBundles.isEmpty()){
            totalPrice.setText("$0");
        }
    }
}