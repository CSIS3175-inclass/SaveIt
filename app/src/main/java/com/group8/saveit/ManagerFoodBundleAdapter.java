package com.group8.saveit;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManagerFoodBundleAdapter extends RecyclerView.Adapter<ManagerFoodBundleAdapter.BundleViewHolder> {
    private ArrayList<FoodBundle> bundles;
    private com.group8.saveit.DatabaseHelper dbHelper;
    Context context;

    public ManagerFoodBundleAdapter(ArrayList<FoodBundle> bundles, com.group8.saveit.DatabaseHelper dbHelper, Context context) {
        this.bundles = bundles;
        this.dbHelper = dbHelper;
        this.context=context;
    }

    public ManagerFoodBundleAdapter(ArrayList<FoodBundle> bundles) {
        this.bundles = bundles;
    }

    @NonNull
    @Override
    public BundleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bundle_for_list, parent, false);
        return new BundleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BundleViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FoodBundle bundle = bundles.get(holder.getAdapterPosition());
        holder.idTextView.setText("Bundle ID: " + bundle.getId());
        holder.priceTextView.setText("$" + bundle.getPrice());
        Log.d("test","items: "+bundle.getItems());

        if (bundle.getItems() != null) {
            String items="";
            for(int i=0;i<bundle.getItems().length;i++){
                items+=bundle.getItems()[i]+",";
            }
            holder.itemsTextView.setText(items);
        } else {
            holder.itemsTextView.setText("No items in bundle");
        }

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper !=null)
                {
                    long id = bundle.getId();
                    dbHelper.deleteBundle((int) id);
                    bundles.remove(bundle);
                    notifyItemRemoved(position);
                }
                else
                {
                    Log.e(TAG, "Database helper is null");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return bundles.size();
    }


    public static class BundleViewHolder extends RecyclerView.ViewHolder {
        TextView priceTextView;
        TextView itemsTextView;
        TextView idTextView;
        ImageButton deleteButton;

        public BundleViewHolder(View itemView) {
            super(itemView);
            priceTextView = itemView.findViewById(R.id.txtPrice);
            itemsTextView = itemView.findViewById(R.id.txtListItems);
            idTextView = itemView.findViewById(R.id.txtBundleID);
            deleteButton = itemView.findViewById(R.id.ibtnDeleteBundle);
        }
    }
}
