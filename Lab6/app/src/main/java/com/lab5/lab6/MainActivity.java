package com.lab5.lab6;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnNumbers).setOnClickListener(v ->
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new BaseFragment())
                        .addToBackStack(null).commit());

        findViewById(R.id.btnCurrency).setOnClickListener(v ->
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new CurrencyConverterFragment())
                        .addToBackStack(null).commit());

        findViewById(R.id.btnLength).setOnClickListener(v ->
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new LengthFragment())
                        .addToBackStack(null).commit());

        findViewById(R.id.btnArea).setOnClickListener(v ->
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new AreaFragment())
                        .addToBackStack(null).commit());
    }
}