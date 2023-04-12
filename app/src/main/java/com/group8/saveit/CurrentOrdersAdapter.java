package com.group8.saveit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class CurrentOrdersAdapter extends RecyclerView.Adapter<CurrentOrdersAdapter.OrderViewHolder> {
    ArrayList<CustomerOrder> customerOrders;
    LayoutInflater layoutInflater;
    Context context;
    String customerEmail;

    public CurrentOrdersAdapter(ArrayList<CustomerOrder> customerOrders, Context context) {
        this.customerOrders = customerOrders;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(context).inflate(R.layout.order_inprogress_item,parent,false);

        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        CustomerOrder customerOrder = customerOrders.get(position);
        Log.i("test","Order number: "+customerOrder.getOrderId());
        holder.customer.setText(customerOrder.getCustomerEmail());
        holder.orderId.setText(Integer.toString(customerOrder.getOrderId()));
        holder.date.setText(customerOrder.getOrderDate());
        holder.deliveryOption.setText(customerOrder.getDeliveryOption());
        holder.deliveryAddress.setText(customerOrder.getAddress());
        ArrayList<HashMap<String,String>> foodBundleList = new ArrayList<>();

        //bind customer's ordered foodbundle to listview
        String[] from = {"foodBundle"};
        int[] to = {R.id.subitem};
        ArrayList foodBundles = customerOrder.getOrderedFoodBundles();
        for(int i =0; i<foodBundles.size();i++){
            HashMap<String,String> hashMap = new HashMap<>();
            FoodBundle foodBundle = (FoodBundle) foodBundles.get(0);
            hashMap.put("foodBundle",foodBundle.getBundleName());
            foodBundleList.add(hashMap);
        }
        SimpleAdapter foodBundleAdapter = new SimpleAdapter(layoutInflater.getContext(),
                                                            foodBundleList,
                                                            R.layout.food_bundle_subitem,
                                                            from,
                                                            to);
        holder.foodBundles.setAdapter(foodBundleAdapter);

        //Set listview's height to match the number of subitems
        int listViewHeight = 0;
        for (int i = 0; i < foodBundles.size(); i++) {
            View listItem = foodBundleAdapter.getView(i, null, holder.foodBundles);
            listItem.measure(0,0);
            listViewHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = holder.foodBundles.getLayoutParams();
        params.height = listViewHeight + (holder.foodBundles.getDividerHeight() * (foodBundles.size() - 1));
        holder.foodBundles.setLayoutParams(params);

        holder.sendReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Reminder sent to customer "+customerOrder.getCustomerEmail(), Toast.LENGTH_LONG).show();
            }
        });
        holder.completedCheck.setChecked(customerOrder.isCompleted());

        holder.completedCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            databaseHelper.updateOrderStatus(customerOrder.getOrderId(),isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return customerOrders.size();
    }

    public class  OrderViewHolder extends RecyclerView.ViewHolder {
        TextView customer;
        TextView date;
        TextView orderId;
        TextView deliveryOption;
        TextView deliveryAddress;
        ListView foodBundles;
        ImageButton sendReminderBtn;
        CheckBox completedCheck;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            customer = itemView.findViewById(R.id.customer2);
            date = itemView.findViewById(R.id.orderDate);
            orderId = itemView.findViewById(R.id.orderID2);
            deliveryOption = itemView.findViewById(R.id.deliveryType2);
            deliveryAddress = itemView.findViewById(R.id.deliveryAddress2);
            foodBundles = itemView.findViewById(R.id.orderedBundles);
            sendReminderBtn = itemView.findViewById((R.id.sendReminderBtn));
            completedCheck = itemView.findViewById((R.id.completedCheck));
        }
    }
}
