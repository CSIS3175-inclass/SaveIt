package com.group8.saveit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteRestaurantAdapter extends RecyclerView.Adapter {

    private String[] arr1;
    private int arr2[];
    Context context;
    FavoriteItemClickListner clickListner;

    FavoriteRestaurantAdapter(Context context, String arr1[], int arr2[], FavoriteItemClickListner clklsnr){
        this.context = context;
        this.arr1=arr1;
        this.arr2=arr2;
        this.clickListner = clklsnr;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favorite_restraunt_row,parent,false);
        FavoriteItemView dd = new FavoriteItemView(view);
        return dd;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((FavoriteItemView)holder).restaurantNametextView.setText(arr1[position]);
        ((FavoriteItemView)holder).restaurantImageView.setImageResource(arr2[position]);
    }

    @Override
    public int getItemCount() {
        return arr1.length;
    }


    class FavoriteItemView extends  RecyclerView.ViewHolder {

        TextView restaurantNametextView;
        ImageView restaurantImageView;

        public FavoriteItemView(@NonNull View itemView) {
            super(itemView);
            this.restaurantImageView = itemView.findViewById(R.id.restaurantImage);
            this.restaurantNametextView = itemView.findViewById(R.id.restaurantName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    clickListner.itemCLicked(view,getAdapterPosition());
                }
            });

        }
    }


    interface FavoriteItemClickListner {

        public void itemCLicked(View view, int position);
    }

}
