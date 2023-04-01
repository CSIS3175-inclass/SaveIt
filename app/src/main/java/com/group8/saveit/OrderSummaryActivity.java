package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class OrderSummaryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    double total=0;
    TextView totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        Intent intent=getIntent();
        totalPrice = findViewById(R.id.totalPrice);
        if(intent!=null){
            ArrayList<FoodBundle> selectedFoodBundles=(ArrayList<FoodBundle>) intent.getSerializableExtra("selectedFoodBundles");
            Log.i("test",selectedFoodBundles.get(0).getBundleName());
            recyclerView=findViewById(R.id.orderRecyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(this,1));
            recyclerView.setHasFixedSize(true);
            OrderSummaryAdapter adapter=new OrderSummaryAdapter(this,selectedFoodBundles);
            recyclerView.setAdapter(adapter);
            for(int i = 0; i< selectedFoodBundles.size();i++){
                total+=selectedFoodBundles.get(i).getPrice();
            }
            totalPrice.setText("$"+Double.toString(total));
            adapter.notifyDataSetChanged();
        }


    }
}