package com.group8.saveit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

//Adapter for recycler view in order summary activity
public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.OrderViewHolder> {
    Context context;
    ArrayList<FoodBundle> foodBundles;
    LayoutInflater layoutInflater;

    public OrderSummaryAdapter(Context context, ArrayList<FoodBundle> foodBundles) {
        this.context = context;
        this.foodBundles = foodBundles;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public void deleteFoodBundle(int position){
        foodBundles.remove(position);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.from(context).inflate(R.layout.food_order_item,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int position) {
        FoodBundle foodBundle=foodBundles.get(position);
        orderViewHolder.bundleName.setText(foodBundle.getBundleName());
        orderViewHolder.bundlePrice.setText("$"+Double.toString(foodBundle.getPrice()));

        //bind subitems of FoodBundle object to ood_bundle_item's listView
        String[] from ={"item"};
        int[] to={R.id.subitem};
        String[] items = foodBundle.getItems();
        ArrayList<HashMap<String,String>> itemList = new ArrayList<>();
        for(int i=0; i<items.length;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("item",items[i]);
            itemList.add(hashMap);
        }
        SimpleAdapter listAdapter=new SimpleAdapter(layoutInflater.getContext(),itemList,R.layout.food_bundle_subitem,from,to);
        orderViewHolder.listView.setAdapter(listAdapter);

        orderViewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFoodBundle(orderViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodBundles.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        ImageButton imageButton;
        TextView bundleName;
        TextView bundlePrice;
        ListView listView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton=itemView.findViewById(R.id.deleteBtn);
            bundleName=itemView.findViewById(R.id.bundleName2);
            bundlePrice=itemView.findViewById(R.id.bundlePrice2);
            listView=itemView.findViewById(R.id.itemsList);


            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteFoodBundle(getAdapterPosition());
                }
            });
        }
    }
}
