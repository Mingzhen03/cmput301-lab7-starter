package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Displays the tapped city name and provides a Back button.
 */
public class ShowActivity extends AppCompatActivity {

    /** Intent extra key for passing the city name. */
    public static final String EXTRA_CITY_NAME =
            "com.example.androiduitesting.EXTRA_CITY_NAME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView cityText = findViewById(R.id.text_city);
        Button back = findViewById(R.id.button_back);

        // Read city name from intent and show it
        Intent intent = getIntent();
        String cityName = intent.getStringExtra(EXTRA_CITY_NAME);
        if (cityName != null) {
            cityText.setText(cityName);
        }

        // Go back to MainActivity
        back.setOnClickListener(v -> finish());
    }
}

