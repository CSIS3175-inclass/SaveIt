package com.group8.saveit;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class UserMenuFragment extends Fragment {
    String customerEmail;
    ImageButton home;
    ImageButton history;
    ImageButton profile;


    public UserMenuFragment() {

    }
    public UserMenuFragment(String customerEmail) {
        this.customerEmail=customerEmail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        home = view.findViewById(R.id.menu_home_icon);
        history = view.findViewById(R.id.menu_history_icon);
        profile = view.findViewById(R.id.menu_profile_icon);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),RestaurantSearch.class));
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),OrderHistory.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), userEdit.class));
            }
        });
    }
}