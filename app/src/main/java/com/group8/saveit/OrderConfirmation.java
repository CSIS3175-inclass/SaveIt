package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class OrderConfirmation extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    String customerEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if(intent!=null){
            //get data from OrderSummaryActivity
            int newOrderId =intent.getIntExtra("orderId",0);
            String restaurantNameTxt=intent.getStringExtra("restaurantName");
            String addressTxt=intent.getStringExtra("address");
            String statusTxt=intent.getStringExtra("status");
            double totalNum=intent.getDoubleExtra("total",0);
            String deliveryOption=intent.getStringExtra("delivery");
            int restaurantId =intent.getIntExtra("restaurantId",0);
            customerEmail = intent.getStringExtra("customerEmail");

            TextView orderId = findViewById(R.id.textViewOrderId);
            TextView restaurantName = findViewById(R.id.textViewRestaurantName);
            TextView address = findViewById(R.id.textViewAddress);
            TextView status = findViewById(R.id.textViewStatus);
            TextView total = findViewById(R.id.textViewTotal);
            TextView delivery = findViewById(R.id.textDeliveryOption);

            orderId.setText(Integer.toString(newOrderId));
            restaurantName.setText(restaurantNameTxt);
            address.setText(addressTxt);
            status.setText(statusTxt);
            total.setText("$"+totalNum);
            delivery.setText(deliveryOption);
            Button backToReastaurant = findViewById(R.id.btnBackToRestaurant);

            backToReastaurant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //take back to restaurant page
                    Intent restaurantActivityIntent = new Intent(OrderConfirmation.this,RestaurantActivity.class);
                    restaurantActivityIntent.putExtra("restaurantId",restaurantId);
                    restaurantActivityIntent.putExtra("customerEmail",customerEmail);
                    startActivity(restaurantActivityIntent);
                }
            });

            UserMenuFragment userMenuFragment = new UserMenuFragment(customerEmail);
            replaceFragment(userMenuFragment);

        }



    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.navigationContainerView5,fragment);
        transaction.commit();
    }
}