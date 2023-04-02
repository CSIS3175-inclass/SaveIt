package com.group8.saveit;

import android.content.Context;
import android.util.Log;
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
    TextView totalPrice;


    public OrderSummaryAdapter(Context context, ArrayList<FoodBundle> foodBundles, TextView totalPrice) {
        this.context = context;
        this.foodBundles = foodBundles;
        this.layoutInflater = LayoutInflater.from(context);
        this.totalPrice = totalPrice;
    }
    public void deleteFoodBundle(int position){
        foodBundles.remove(position);
    }

    private OnDataChangedListener onDataChangedListener;

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }

    private void dataChanged() {
        if (onDataChangedListener != null) {
            onDataChangedListener.onDataChanged();
        }
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

        //Set listview's height to match the number of subitems
        int listViewHeight = 0;
        for (int i = 0; i < items.length; i++) {
            View listItem = listAdapter.getView(i, null, orderViewHolder.listView);
            listItem.measure(0,0);
            listViewHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = orderViewHolder.listView.getLayoutParams();
        params.height = listViewHeight + (orderViewHolder.listView.getDividerHeight() * (items.length - 1));
        orderViewHolder.listView.setLayoutParams(params);

        //update order's total price
        double total = 0;
        for(int i = 0; i< foodBundles.size();i++){
            total+=foodBundles.get(i).getPrice();
        }
        totalPrice.setText("$"+Double.toString(total));

        orderViewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = orderViewHolder.getAdapterPosition();
                Log.i("test","deleting the order at index "+position);
                //check if index of item to be deleted is valid
                if(position!=RecyclerView.NO_POSITION){
                    deleteFoodBundle(position);
                    dataChanged();
                    notifyDataSetChanged(); //required to update the UI
                }

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

    public interface OnDataChangedListener { //listener to allow notifyDataSetChanged() to update view even when there's no more visible view
        void onDataChanged();
    }
}
