package com.group8.saveit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class managerMenuFragment extends Fragment {
    int restaurantId;
    ImageButton home;
    ImageButton history;
    ImageButton profile;

    public managerMenuFragment() {
        // Required empty public constructor
    }
    public managerMenuFragment(int restaurantId) {
        this.restaurantId=restaurantId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manager_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        home = view.findViewById(R.id.home_icon_2);
        history = view.findViewById(R.id.history_icon_manager);
        profile = view.findViewById(R.id.profile_icon_2);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prevIntent = getActivity().getIntent();
                int rid = prevIntent.getIntExtra("restaurantId",0);
                Intent newIntent = new Intent(getContext(),HomeManagerActivity.class);
                newIntent.putExtra("restaurantId",rid);
                startActivity(newIntent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("test","manager history");
                Intent prevIntent = getActivity().getIntent();
                int rid = prevIntent.getIntExtra("restaurantId",0);
                Intent newIntent = new Intent(getContext(),CurrentOrdersActivity.class);
                newIntent.putExtra("restaurantId",rid);
                startActivity(newIntent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Return to the login activity
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}