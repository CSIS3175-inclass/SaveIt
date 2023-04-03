package com.group8.saveit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class OrderHistoryAdapter extends RecyclerView.Adapter {

    Context context;
    OrderHistoryAdapter(Context context){
        this.context = context;
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
//        ((OrderView)holder).textViewOrderId.setText("OrderId:" + position);
//        ((OrderView)holder).textViewDate.setText();
//        ((OrderView)holder).textViewRestaurantName.setText();
//        ((OrderView)holder).textViewBundles.setText();
//        ((OrderView)holder).textViewTotal.setText();
//        ((OrderView)holder).textViewOrderStatus.setText();

    }

    @Override
    public int getItemCount() {
        return 5;
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
