package com.group8.saveit;

import static android.os.Build.VERSION_CODES.S;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Set;

public class OrderSummaryActivity extends AppCompatActivity implements OrderSummaryAdapter.OnDataChangedListener{
    RecyclerView recyclerView;
    double total;
    TextView totalPrice;
    ArrayList<FoodBundle> foodBundles;

    Button completeOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        total=0;
        Intent intent=getIntent();
        totalPrice = findViewById(R.id.totalPrice);
        completeOrder=findViewById(R.id.confirmOrderBtn);
        if(intent!=null){
            ArrayList<FoodBundle> selectedFoodBundles=(ArrayList<FoodBundle>) intent.getSerializableExtra("selectedFoodBundles");
            foodBundles=selectedFoodBundles;
            recyclerView=findViewById(R.id.orderRecyclerView);
//            recyclerView.setLayoutManager(new GridLayoutManager(this,1));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            OrderSummaryAdapter adapter=new OrderSummaryAdapter(this,selectedFoodBundles,totalPrice);
            adapter.setOnDataChangedListener(this);
            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }

        completeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(foodBundles.isEmpty()){
                    Log.i("test","Please select a food bundle before placing your order in "+ OrderSummaryActivity.this);
//                    Toast.makeText(OrderSummaryActivity.this,"Please select a food bundle before placing your order",Toast.LENGTH_LONG).show();
                    Toast.makeText(OrderSummaryActivity.this,
                            "Please select a food bundle before placing your order",
                            Toast.LENGTH_LONG).show();
                }
                else{
//                    CustomerOrder newOrder = new CustomerOrder();
                    int orderId;
                    String restaurantName;
                    String address;
                    String status;

//                    startActivity(new Intent(OrderSummaryActivity.this,CurrentOrdersActivity.class));
                    Intent orderConfirmationIntent = new Intent(OrderSummaryActivity.this,OrderConfirmation.class);
                    startActivity(orderConfirmationIntent);
                }
            }
        });
    }

    @Override
    public void onDataChanged() {
        //update order's total price
        if(foodBundles.isEmpty()){
            totalPrice.setText("$0");
        }
    }
}