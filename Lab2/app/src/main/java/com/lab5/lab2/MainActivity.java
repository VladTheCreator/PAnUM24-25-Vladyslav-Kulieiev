package com.lab5.lab2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber;
    private Button btnArabToRoman, btnRomanToArab;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = findViewById(R.id.etNumber);
        btnArabToRoman = findViewById(R.id.btnArabToRoman);
        btnRomanToArab = findViewById(R.id.btnRomanToArab);
        tvResult = findViewById(R.id.tvResult);

        btnArabToRoman.setOnClickListener(v -> {
            String input = etNumber.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(this, "Wprowadź liczbę arabską", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int number = Integer.parseInt(input);
                if (number <= 0 || number > 3999) {
                    Toast.makeText(this, "Podaj liczbę od 1 do 3999", Toast.LENGTH_SHORT).show();
                    return;
                }
                String roman = toRoman(number);
                tvResult.setText("Rzymska: " + roman);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Nieprawidłowa liczba", Toast.LENGTH_SHORT).show();
            }
        });

        btnRomanToArab.setOnClickListener(v -> {
            String input = etNumber.getText().toString().trim().toUpperCase();
            if (input.isEmpty()) {
                Toast.makeText(this, "Wprowadź liczbę rzymską", Toast.LENGTH_SHORT).show();
                return;
            }

            int arabic = romanToInt(input);
            if (arabic == -1) {
                Toast.makeText(this, "Nieprawidłowa liczba rzymska", Toast.LENGTH_SHORT).show();
                return;
            }
            tvResult.setText("Arabska: " + arabic);
        });
    }

    private String toRoman(int number) {
        String[] m = {"", "M", "MM", "MMM"};
        String[] c = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] x = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] i = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return m[number / 1000] + c[(number % 1000) / 100] + x[(number % 100) / 10] + i[number % 10];
    }

    private int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) return -1;
            int value = map.get(c);

            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
                prevValue = value;
            }
        }
        return result;
    }
}