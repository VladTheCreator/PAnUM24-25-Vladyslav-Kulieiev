package com.lab5.lab5_2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnDrink).setOnClickListener(v ->
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new DrinkFragment())
                        .addToBackStack(null)
                        .commit()
        );

        findViewById(R.id.btnSnack).setOnClickListener(v ->
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new SnackFragment())
                        .addToBackStack(null)
                        .commit()
        );

        findViewById(R.id.btnLocation).setOnClickListener(v ->
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new LocationFragment())
                        .addToBackStack(null)
                        .commit()
        );
    }
}