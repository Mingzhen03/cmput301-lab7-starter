package com.example.androiduitesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // UI refs
    ListView cityList;
    EditText newName;
    LinearLayout nameField;

    // Data
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.field_nameEntry);
        newName   = findViewById(R.id.editText_name);
        cityList  = findViewById(R.id.city_list);

        dataList = new ArrayList<>();

        // Uses row layout "content.xml" whose TextView id must be @android:id/text1
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        final Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(v -> nameField.setVisibility(View.VISIBLE));

        final Button confirmButton = findViewById(R.id.button_confirm);
        confirmButton.setOnClickListener(v -> {
            String cityName = newName.getText().toString().trim();
            if (!cityName.isEmpty()) {
                cityAdapter.add(cityName);
            }
            newName.getText().clear();
            nameField.setVisibility(View.INVISIBLE);
        });

        final Button deleteButton = findViewById(R.id.button_clear);
        deleteButton.setOnClickListener(v -> cityAdapter.clear());

        // NEW: open ShowActivity when a city is tapped
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedCity = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                intent.putExtra(ShowActivity.EXTRA_CITY_NAME, clickedCity);
                startActivity(intent);
            }
        });
    }
}
