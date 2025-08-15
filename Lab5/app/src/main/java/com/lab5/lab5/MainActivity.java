package com.lab5.lab5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnDrink).setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new DrinkFragment())
                    .addToBackStack(null)
                    .commit();
        });

        findViewById(R.id.btnSnack).setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new SnackFragment())
                    .addToBackStack(null)
                    .commit();
        });

        findViewById(R.id.btnLocation).setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new LocationFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }
}