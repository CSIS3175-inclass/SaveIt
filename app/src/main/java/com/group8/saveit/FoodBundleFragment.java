package com.group8.saveit;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

//implement CustomAdapter.OnFoodBundleCheckedListener to access updated total price and update Textview accordingly
public class FoodBundleFragment extends Fragment implements FoodBundleAdapter.OnFoodBundleCheckedListener {
    private ArrayList<FoodBundle> foodBundles = new ArrayList<>();
    private RecyclerView recyclerView;
    private double total = 0.0;
    private DatabaseHelper databaseHelper;
    private int restaurantId;

    public FoodBundleFragment() {
    }
    public FoodBundleFragment(DatabaseHelper databaseHelper, int restaurantId) {
        this.databaseHelper=databaseHelper;
        this.restaurantId=restaurantId;
        loadData(restaurantId);

    }

    public double getTotal() {
        return total;
    }

    public ArrayList<FoodBundle> getFoodBundles() {
        return foodBundles;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_bundle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.foodb_recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager
                (getContext(),1));
        recyclerView.setHasFixedSize(true);
        FoodBundleAdapter myAdapter = new FoodBundleAdapter(getContext(),foodBundles, this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    private void loadData(int restaurantId){
        //Only display available food bundles
        ArrayList<FoodBundle> restaurantFoodBundles = databaseHelper.getFoodBundleByRestaurant(restaurantId);
        for(int i=0;i<restaurantFoodBundles.size();i++){
            if(restaurantFoodBundles.get(i).isAvailable())
                foodBundles.add(restaurantFoodBundles.get(i));
        }
    }

    //updates totalValue Textview on every checkbox check
    @Override
    public void onFoodBundleChecked(double price) {
        total += price;
       TextView totalTxt = getActivity().findViewById(R.id.totalValue);
       totalTxt.setText("$"+Double.toString(total));
    }
}