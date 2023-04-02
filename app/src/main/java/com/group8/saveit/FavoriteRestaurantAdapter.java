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
    FavortiteItemClickListner clickListner;

    FavoriteRestaurantAdapter(Context context, String arr1[], int arr2[]){
        this.context = context;
        this.arr1=arr1;
        this.arr2=arr2;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favorite_restraunt_row,parent,false);
        FavroiteItemView dd = new FavroiteItemView(view);
        return dd;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((FavroiteItemView)holder).restaurantNametextView.setText(arr1[position]);
        ((FavroiteItemView)holder).restaurantImageView.setImageResource(arr2[position]);
    }

    @Override
    public int getItemCount() {
        return 4;
    }


    class FavroiteItemView extends  RecyclerView.ViewHolder {

        TextView restaurantNametextView;
        ImageView restaurantImageView;

        public FavroiteItemView(@NonNull View itemView) {
            super(itemView);
            this.restaurantImageView = itemView.findViewById(R.id.restaurantImage);
            this.restaurantNametextView = itemView.findViewById(R.id.restaurantName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListner.itemCLicked();
                }
            });

        }
    }


    interface FavortiteItemClickListner {

        public void itemCLicked();
    }

}
