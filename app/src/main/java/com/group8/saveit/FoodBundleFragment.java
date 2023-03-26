package com.group8.saveit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

//implement CustomAdapter.OnFoodBundleCheckedListener to access updated total price and update Textview accordingly
public class FoodBundleFragment extends Fragment implements CustomAdapter.OnFoodBundleCheckedListener {
    private ArrayList<FoodBundle> foodBundles = new ArrayList<>();
    private RecyclerView recyclerView;
    private double total = 0.0;

    public FoodBundleFragment() {
    }

    public double getTotal() {
        return total;
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
        loadData();
        recyclerView = view.findViewById(R.id.foodb_recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager
                (getContext(),1));
        recyclerView.setHasFixedSize(true);
        CustomAdapter myAdapter = new CustomAdapter(getContext(),foodBundles, this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    private void loadData(){
        FoodBundle foodBundleOne = new FoodBundle("FoodBundleOne",R.drawable.imag1,10.99);
        FoodBundle foodBundleTwo = new FoodBundle("FoodBundleTwo",R.drawable.image2,9.99);
        String[] items ={"item1","item2"};
        foodBundleOne.setItems(items);
        foodBundleTwo.setItems(items);
        foodBundles.add(foodBundleOne);
        foodBundles.add(foodBundleTwo);
    }

    //updates totalValue Textview on every checkbox check
    @Override
    public void onFoodBundleChecked(double price) {
        total += price;
       TextView totalTxt = getActivity().findViewById(R.id.totalValue);
       totalTxt.setText("$"+Double.toString(total));
    }
}