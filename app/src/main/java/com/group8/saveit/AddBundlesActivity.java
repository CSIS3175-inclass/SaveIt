package com.group8.saveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AddBundlesActivity extends AppCompatActivity {

    com.group8.saveit.DatabaseHelper DatabaseHelper;

    LinearLayout listContainer;
    Button addButton;
    int numClicks = 0;
    Button saveButton;
    EditText edTxtPrice;
    EditText edTxtBundleName;
    SharedPreferences sharedPreferences;
    String managerRID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bundles);
        DatabaseHelper = new com.group8.saveit.DatabaseHelper(this);
        listContainer = findViewById(R.id.list_container);
        addButton = findViewById(R.id.addButton);
        saveButton = findViewById(R.id.btnSave);
        edTxtPrice = findViewById(R.id.edTxtPrice);

        sharedPreferences = getSharedPreferences("MyPreferences",MODE_PRIVATE);
        managerRID = sharedPreferences.getString("RID","");

        addButton.setOnClickListener(v -> {
            if (numClicks < 3) {
                numClicks++;
                View listItem = getLayoutInflater().inflate(R.layout.bundle_items, listContainer, false);
                ImageButton removeButton = listItem.findViewById(R.id.remove_button);
                EditText editText = listItem.findViewById(R.id.edit_text);

                switch (numClicks) {
                    case 1:
                        editText.setHint("Enter item 1");
                        editText.setId(R.id.edit_text1);
                        break;
                    case 2:
                        editText.setHint("Enter item 2");
                        editText.setId(R.id.edit_text2);
                        break;
                    case 3:
                        editText.setHint("Enter item 3");
                        editText.setId(R.id.edit_text3);
                        break;
                }


                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                layoutParams.topMargin = 16;
                layoutParams.bottomMargin = 16;

                listContainer.addView(listItem, layoutParams);

                removeButton.setOnClickListener(v1 -> {
                    listContainer.removeView(listItem);
                    numClicks--;
                });
            } else {
                Toast.makeText(this, "Maximum items reached", Toast.LENGTH_SHORT).show();
            }


        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View v) {
                String items = "";
                for (int i = 1; i <= numClicks; i++) {
                    EditText editText = findViewById(getResources().getIdentifier("edit_text" + i, "id", getPackageName()));
                    String input = editText.getText().toString();
                    items += input + ",";
                }
                sharedPreferences = getSharedPreferences("MyPreferences",MODE_PRIVATE);
                managerRID = sharedPreferences.getString("managerRID","");
                String price = edTxtPrice.getText().toString();
                isInserted = DatabaseHelper.addBundleData(Integer.parseInt(price),items, managerRID);

                if(isInserted)
                {
                    Toast.makeText(AddBundlesActivity.this, "Bundle Added!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(AddBundlesActivity.this, "Bundle not added", Toast.LENGTH_LONG).show();
                }

                startActivity(new Intent(AddBundlesActivity.this, HomeManagerActivity.class));
            }
        });


    }
}