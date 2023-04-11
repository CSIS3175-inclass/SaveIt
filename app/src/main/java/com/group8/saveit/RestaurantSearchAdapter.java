package com.group8.saveit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestaurantSearchAdapter extends BaseAdapter {

    Context ctx;
    String arr1[];
    int arr2[];
    LayoutInflater inflater;
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    DatabaseHelper databaseHelper;
    String customerEmail;

    public RestaurantSearchAdapter(Context ctx,ArrayList<Restaurant> restaurants, String customerEmail){
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
        this.restaurants=restaurants;
        databaseHelper=new DatabaseHelper(ctx);
        this.customerEmail=customerEmail;

        String[] arr1 = new String[restaurants.size()];
        int[] arr2 = new int[restaurants.size()];
        for(int i = 0; i<restaurants.size();i++){
            arr1[i]=restaurants.get(i).getName();
            arr2[i]=R.drawable.pic;
        }

        this.arr1 = arr1;
        this.arr2 = arr2;
    }
    @Override
    public int getCount() {
        return arr1.length;
    }

    @Override
    public Restaurant getItem(int i) {
        if(i>=0&&i<getCount())
            return restaurants.get(i);
        else
            return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_restaurant_search_row,null);
        TextView textView = (TextView) view.findViewById(R.id.restaurantName);
        ImageView imageView = (ImageView) view.findViewById(R.id.restaurantImage);
        textView.setText(arr1[i]);
        imageView.setImageResource(arr2[i]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //take to corresponding restaurant
                Intent intent = new Intent(ctx,RestaurantActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //this flag is required when starting an activity from outside an activity
                Restaurant restaurant = getItem(i);

                if(restaurant!=null){
                    int restaurantId = restaurant.getRid();
                    ArrayList<FoodBundle> foodBundles = databaseHelper.getFoodBundleByRestaurant(restaurantId);
                    ArrayList<FoodBundle> availableFoodBundles = new ArrayList<>();

                    Log.i("test",restaurant.getName()+" has the following food bundles ");

                    for(int i=0;i<foodBundles.size();i++){
                        if(foodBundles.get(i).isAvailable()){
                            availableFoodBundles.add(foodBundles.get(i));
                        }
                        Log.i("test","/t "+foodBundles.get(i).getBundleName()+" available: "+foodBundles.get(i).isAvailable());
                    }
                    if(!availableFoodBundles.isEmpty()){

                        //pass restaurantId and customer email to OrderSummary activity to create new Order
                        intent.putExtra("restaurantId",restaurantId);
                        intent.putExtra("customerEmail",customerEmail);
                        ctx.startActivity(intent);
                    }
                    else{
                        Toast.makeText(ctx, "This restaurant doesn't have any food bundle available at the moment", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(ctx, "There's an issue with this restaurant please select another one", Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }
}
