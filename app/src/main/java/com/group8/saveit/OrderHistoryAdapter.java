package com.group8.saveit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<CustomerOrder> orders;
    DatabaseHelper databaseHelper;

    OrderHistoryAdapter(Context context, ArrayList<CustomerOrder> orders){
        this.context = context;
        this.orders = orders;
        databaseHelper = new DatabaseHelper(context);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order_history_row,parent,false);
        OrderView orderView = new OrderView(view);
        return orderView;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CustomerOrder order = orders.get(position);
        int orderId = order.getOrderId();

        Restaurant restaurant = databaseHelper.getRestaurantByOrderID(orderId);
        ArrayList<FoodBundle> foodBundles = order.getOrderedFoodBundles();
        double totalPrice = 0;

        ((OrderView)holder).textViewOrderId.setText("OrderId: " + order.getOrderId());
        ((OrderView)holder).textViewDate.setText("Date: " + order.getOrderDate());
        ((OrderView)holder).textViewRestaurantName.setText("RestaurantName: " + restaurant.getName());

        String bundles = "";
        for(int i=0;i<foodBundles.size();i++){
            totalPrice+=foodBundles.get(i).getPrice();
            if(i==foodBundles.size()-1){
                bundles = bundles + "Bundle Id: " +foodBundles.get(i).getId();
            }else{
                bundles = bundles + "Bundle Id: " + foodBundles.get(i).getId() +"\n";
            }
        }

        ((OrderView)holder).textViewBundles.setText(bundles);
        ((OrderView)holder).textViewTotal.setText("Total: $" + totalPrice);
        ((OrderView)holder).textViewOrderStatus.setText("Order Status: " + (order.isCompleted()? "completed":"in-progress"));

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderView extends RecyclerView.ViewHolder{
    TextView textViewOrderId;
    TextView textViewDate;
    TextView textViewRestaurantName;
    TextView textViewBundles;
    TextView textViewTotal;
    TextView textViewOrderStatus;

        public OrderView(@NonNull View itemView) {
            super(itemView);
            this.textViewOrderId = itemView.findViewById(R.id.textViewOrderId);
            this.textViewDate = itemView.findViewById(R.id.textViewDate);
            this.textViewRestaurantName = itemView.findViewById(R.id.textViewRestaurantName);
            this.textViewBundles = itemView.findViewById(R.id.textViewBundles);
            this.textViewTotal = itemView.findViewById(R.id.textViewTotal);
            this.textViewOrderStatus = itemView.findViewById(R.id.textViewOrderStatus);
        }
    }
}
