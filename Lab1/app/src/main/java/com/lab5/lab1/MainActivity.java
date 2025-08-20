package com.lab5.lab1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber;
    private Button btnConvert;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = findViewById(R.id.etNumber);
        btnConvert = findViewById(R.id.btnConvert);
        tvResult = findViewById(R.id.tvResult);

        btnConvert.setOnClickListener(v -> {
            String input = etNumber.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(this, "Wprowadź liczbę", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int number = Integer.parseInt(input);
                if (number <= 0 || number > 3999) {
                    Toast.makeText(this, "Podaj liczbę od 1 do 3999", Toast.LENGTH_SHORT).show();
                    return;
                }
                String roman = toRoman(number);
                tvResult.setText("Wynik: " + roman);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Nieprawidłowa liczba", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Konwersja na system rzymski
    private String toRoman(int number) {
        String[] m = {"", "M", "MM", "MMM"};
        String[] c = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] x = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] i = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return m[number/1000] + c[(number%1000)/100] + x[(number%100)/10] + i[number%10];
    }
}