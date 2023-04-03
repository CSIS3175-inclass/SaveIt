package com.group8.saveit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RestrauntSearchAdapter extends BaseAdapter {

    Context ctx;
    String arr1[];
    int arr2[];
    LayoutInflater inflater;

    public RestrauntSearchAdapter(Context ctx, String arr1[], int arr2[]){
        this.ctx = ctx;
        this.arr1 = arr1;
        this.arr2 = arr2;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return arr1.length;
    }

    @Override
    public Object getItem(int i) {
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
        return view;
    }
}
