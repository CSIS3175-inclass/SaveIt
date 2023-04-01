package com.group8.saveit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.HashMap;

//Adapter for recycler view in FoodBundleFragment
public class FoodBundleAdapter extends RecyclerView.Adapter<FoodBundleAdapter.MyViewHolder> {
    Context context;
    ArrayList<FoodBundle> FoodBundles;
    LayoutInflater layoutInflater;
    private OnFoodBundleCheckedListener onFoodBundleCheckedListener;

    public void setOnFoodBundleCheckedListener(OnFoodBundleCheckedListener listener) {
        this.onFoodBundleCheckedListener = listener;
    }
    public interface OnFoodBundleCheckedListener {
        void onFoodBundleChecked(double price);
    }

    public FoodBundleAdapter(Context context, ArrayList<FoodBundle> foodBundles, OnFoodBundleCheckedListener onFoodBundleCheckedListener) {
        this.context = context;
        FoodBundles = foodBundles;
        this.layoutInflater = LayoutInflater.from(context);
        this.onFoodBundleCheckedListener = onFoodBundleCheckedListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(context).inflate(R.layout.food_bundle_item,parent,false);

        return new MyViewHolder(v);
    }

    //Bind FoodBundle object to a food_bundle_item instance
    //called on every updates to recycler view's row(s)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FoodBundle foodBundle=FoodBundles.get(position);
        holder.imageView.setImageResource(foodBundle.getBundleImage());
        holder.name.setText(foodBundle.getBundleName());
        holder.price.setText("$" + Double.toString(foodBundle.getPrice()));

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
        holder.listView.setAdapter(listAdapter);

        // set the height of the ListView dynamically
        int listViewHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(holder.listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, holder.listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            listViewHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = holder.listView.getLayoutParams();
        params.height = listViewHeight + (holder.listView.getDividerHeight() * (listAdapter.getCount() - 1));
        holder.listView.setLayoutParams(params);

        //checkbox listener
        //update total price accordingly
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    onFoodBundleCheckedListener.onFoodBundleChecked(foodBundle.getPrice());
                    foodBundle.setChecked(isChecked); //set isChecked to true when foodbundle is checked
                } else {
                    onFoodBundleCheckedListener.onFoodBundleChecked(-foodBundle.getPrice());
                    foodBundle.setChecked(isChecked);//set isChecked to false when foodbundle is unchecked
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return FoodBundles.size();
    }

    public FoodBundle getItem(int id){
        return FoodBundles.get(id);
    }
    //initializes UI elements
    public class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        ImageView imageView;
        TextView name;
        TextView price;
        ListView listView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.orderCheck);
            imageView=itemView.findViewById(R.id.bundleImg);
            name=itemView.findViewById(R.id.bundleName);
            price=itemView.findViewById(R.id.bundlePrice);
            listView=itemView.findViewById(R.id.bundleListView);

            //checkbox listener
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    double price = FoodBundles.get(getAdapterPosition()).getPrice();
                    onFoodBundleCheckedListener.onFoodBundleChecked(price);
                }
            });
        }
    }

}
