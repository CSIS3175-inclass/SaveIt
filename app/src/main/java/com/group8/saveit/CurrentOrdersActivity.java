package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;

public class CurrentOrdersActivity extends AppCompatActivity {
    ArrayList<CustomerOrder> customerOrders = new ArrayList<>();
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    int restaurantId;
    Button generateReport;
    CurrentOrdersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_orders);
        Intent intent = getIntent();
        generateReport = findViewById(R.id.generateReportBtn);

        if(intent!=null){
            restaurantId = intent.getIntExtra("restaurantId",restaurantId);

            databaseHelper = new DatabaseHelper(this);
            recyclerView = findViewById(R.id.currentOrderRecycler);
            loadData();

            if(!customerOrders.isEmpty()){
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setHasFixedSize(true);
                CurrentOrdersAdapter adapter = new CurrentOrdersAdapter(customerOrders,this);
                this.adapter=adapter;
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }

            generateReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        //update data
                        loadData();
                        //generate report (overwrite)
                        FileOutputStream fout = openFileOutput("orderreport.txt",MODE_PRIVATE);
                        String report = "\n Report from "+ Calendar.getInstance().getTime();

                        for(int i =0; i< customerOrders.size();i++){
                            report+=getReport(customerOrders.get(i));
                        }

                        fout.write(report.getBytes(StandardCharsets.UTF_8));
                        fout.close();


                        Toast.makeText(CurrentOrdersActivity.this, "Report has been generated", Toast.LENGTH_SHORT).show();
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            });

            managerMenuFragment managerMenuFragment = new managerMenuFragment(restaurantId);
            replaceFragment(managerMenuFragment);

        }

    }

    public void loadData(){
        Log.d("test","loadind restaurant orders "+restaurantId);

        customerOrders = databaseHelper.getOrdersByRid(restaurantId);
    }
    public String getReport(CustomerOrder customerOrder){
        String ordered = "";
        ArrayList<FoodBundle> foodBundles= customerOrder.getOrderedFoodBundles();
        for(int i=0;i<foodBundles.size();i++){
            FoodBundle foodBundle = foodBundles.get(i);
            ordered += "\n\t\t Food Bundle "+ foodBundle.getId();
            String[] items = foodBundle.getItems();
            for(int j = 0; j< items.length;j++){
                ordered += "\n\t\t\t item "+items[j];
            }
        }

        String report = "\n---"+
                "\nOrderId: "+customerOrder.getOrderId()
                + "\n\tFrom Customer: " + customerOrder.getCustomerEmail()
                + "\n\t"+customerOrder.getDeliveryOption()+" at: " + customerOrder.getDeliveryOption()
                + "\n\tOn : " + customerOrder.getOrderDate()
                + "\n\tCompleted : " + customerOrder.isCompleted()
                + "\n\tOrdered FoodBundles : " + customerOrder.getCustomerEmail()
                + ordered
                ;
        return report;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.navigationContainerView7,fragment);
        transaction.commit();
    }

}