package com.group8.saveit;

import static android.os.Build.VERSION_CODES.S;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

public class OrderSummaryActivity extends AppCompatActivity implements OrderSummaryAdapter.OnDataChangedListener{
    RecyclerView recyclerView;
    double total;
    TextView totalPrice;
    ArrayList<FoodBundle> foodBundles;
    Button completeOrder;
    DatabaseHelper databaseHelper;
    Restaurant restaurant;
    String customerEmail;
    ArrayList<Integer> foodBundlesId=new ArrayList<>();
    EditText editStreet;
    EditText editStreetName;
    EditText editCity;
    EditText editPostalCode;
    String address;
    RadioButton pickupRadio;
    RadioButton deliveryRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        total=0;

        Intent intent=getIntent();

        totalPrice = findViewById(R.id.totalPrice);
        completeOrder=findViewById(R.id.confirmOrderBtn);
        editStreetName = findViewById(R.id.editStreet2);
        editCity = findViewById(R.id.editCity);
        editPostalCode = findViewById(R.id.editPostalCode);
        pickupRadio=findViewById(R.id.option1);
        deliveryRadio=findViewById(R.id.option2);

        databaseHelper = new DatabaseHelper(this);

        if(intent!=null){
            //get customer email, selected foodbundles and their id  from restaurantActivity
            foodBundles=(ArrayList<FoodBundle>) intent.getSerializableExtra("selectedFoodBundles");
            foodBundlesId= (ArrayList<Integer>) intent.getSerializableExtra("selectedFoodBundlesId");

            customerEmail= intent.getStringExtra("customerEmail");
            restaurant=databaseHelper.getRestaurantByID(intent.getIntExtra("restaurantId",0));

            if(customerEmail!=null){
                Customer customer = databaseHelper.getCustomerByEmail(customerEmail);
                editStreetName.setText(customer.getStreetName());
                editCity.setText(customer.getCity());
                editPostalCode.setText(customer.getPostalCode());

                //populate recyclerview
                recyclerView=findViewById(R.id.orderRecyclerView);
//            recyclerView.setLayoutManager(new GridLayoutManager(this,1));
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setHasFixedSize(true);
                OrderSummaryAdapter adapter=new OrderSummaryAdapter(this,foodBundles,totalPrice,foodBundlesId);
                adapter.setOnDataChangedListener(this);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }

            UserMenuFragment userMenuFragment = new UserMenuFragment(customerEmail);
            replaceFragment(userMenuFragment);
        }

        completeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(foodBundles.isEmpty()){
                    Toast.makeText(OrderSummaryActivity.this,
                            "Please select a food bundle before placing your order",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    for(int i =0;i<foodBundles.size();i++){
                        total+=foodBundles.get(i).getPrice();
                    }
                    String deliveryOptionTxt="Pick-up";
                    int deliveryOption=1;
                    //use Restaurant Address if Option is pickup
                    address = restaurant.getStreetName()+" "+restaurant.getCity();


                    if(deliveryRadio.isChecked()){  //else use input restaurant
                        deliveryOptionTxt="Delivery";
                        deliveryOption=2;
                        address = editStreetName.getText()+" "+editPostalCode.getText()+" "+editCity.getText();
                    }


                    String restaurantName=restaurant.getName();

                    CustomerOrder newOrder = new CustomerOrder(customerEmail,deliveryOptionTxt,address);
                    String status=newOrder.isCompleted() ? "completed" : "in-progress";
                    double total=getTotal();

                    long newOrderId = databaseHelper.addCustomerOrder(newOrder.getOrderDate(),
                            newOrder.isCompleted(),
                            restaurant.getRid(),
                            customerEmail,
                            deliveryOption,
                            address);



                    if(newOrderId<0){ //if order couldn't be inserted
                        Toast.makeText(OrderSummaryActivity.this, "The order could not be placed.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //add new record to Order_Bundle for each selected foodBundle;
                        for(int i = 0; i< foodBundlesId.size();i++){
                            databaseHelper.addBundleToOrder((int) newOrderId,foodBundlesId.get(i));
                            //update availability of selected food bundles to false
                            databaseHelper.updateBundleAvailability(foodBundlesId.get(i),false);
                        }
                        newOrder.setOrderId((int) newOrderId);
                        Intent orderConfirmationIntent = new Intent(OrderSummaryActivity.this,OrderConfirmation.class);
                        orderConfirmationIntent.putExtra("orderId",(int) newOrderId);
                        orderConfirmationIntent.putExtra("restaurantName",restaurantName);
                        orderConfirmationIntent.putExtra("address",address);
                        orderConfirmationIntent.putExtra("status",status);
                        orderConfirmationIntent.putExtra("total",total);
                        orderConfirmationIntent.putExtra("delivery",deliveryOptionTxt);
                        orderConfirmationIntent.putExtra("restaurantId",restaurant.getRid());
                        orderConfirmationIntent.putExtra("customerEmail",customerEmail);
                        startActivity(orderConfirmationIntent);
                    }
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

    public double getTotal() {
        return total;
    }
    //navigationContainerView2
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.navigationContainerView2,fragment);
        transaction.commit();
    }
}