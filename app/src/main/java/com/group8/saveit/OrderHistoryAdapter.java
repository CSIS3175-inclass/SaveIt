package com.group8.saveit;

import android.content.Context;
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
    ArrayList<Order> orders;
    OrderHistoryAdapter(Context context, ArrayList<Order> orders){

        this.context = context;
        this.orders = orders;
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
        Order object = orders.get(position);

        ((OrderView)holder).textViewOrderId.setText("OrderId: " + object.getOrderId());
        ((OrderView)holder).textViewDate.setText("Date: " + object.orderDate);
        ((OrderView)holder).textViewRestaurantName.setText("RestaurantName: " + object.getRestaurantName());
        String bundles = "";
            for(int i=0;i<object.getBundles().size();i++){
                if(i==object.getBundles().size()-1){
                    bundles = bundles + "Bundle Id: " +object.getBundles().get(i);
                }else{
                    bundles = bundles + "Bundle Id: " + object.getBundles().get(i) +"\n";
                }
            }

        ((OrderView)holder).textViewBundles.setText(bundles);
        ((OrderView)holder).textViewTotal.setText("Total: $" + object.getTotalPrice());
        ((OrderView)holder).textViewOrderStatus.setText("Order Status: " + object.getOrderStatus());

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
