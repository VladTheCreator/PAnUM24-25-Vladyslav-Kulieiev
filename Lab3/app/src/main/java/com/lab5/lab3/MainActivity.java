package com.lab5.lab3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etPace, etSpeed, etDistance, etTargetDistance, etTargetTime;
    private Button btnFromPace, btnFromSpeed, btnFromTarget;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Powiązanie widoków
        etPace = findViewById(R.id.etPace); // tempo w min/km
        etSpeed = findViewById(R.id.etSpeed); // prędkość w km/h
        etDistance = findViewById(R.id.etDistance); // dystans do obliczenia czasu
        etTargetDistance = findViewById(R.id.etTargetDistance); // dystans do osiągnięcia w określonym czasie
        etTargetTime = findViewById(R.id.etTargetTime); // czas do pokonania dystansu
        btnFromPace = findViewById(R.id.btnFromPace);
        btnFromSpeed = findViewById(R.id.btnFromSpeed);
        btnFromTarget = findViewById(R.id.btnFromTarget);
        tvResult = findViewById(R.id.tvResult);

        // Obliczenia na podstawie tempa
        btnFromPace.setOnClickListener(v -> {
            String paceStr = etPace.getText().toString().trim();
            if (paceStr.isEmpty()) {
                Toast.makeText(this, "Podaj tempo w min/km", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double pace = Double.parseDouble(paceStr); // min/km
                double speed = 60.0 / pace; // km/h

                StringBuilder sb = new StringBuilder();
                sb.append(String.format("Prędkość: %.2f km/h\n", speed));
                sb.append("Czas na maraton: ").append(formatTime(pace * 42.195)).append("\n");
                sb.append("Czas na półmaraton: ").append(formatTime(pace * 21.0975)).append("\n");

                String distStr = etDistance.getText().toString().trim();
                if (!distStr.isEmpty()) {
                    double dist = Double.parseDouble(distStr);
                    sb.append("Czas na ").append(dist).append(" km: ").append(formatTime(pace * dist)).append("\n");
                }

                tvResult.setText(sb.toString());

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Nieprawidłowa wartość tempa", Toast.LENGTH_SHORT).show();
            }
        });

        // Obliczenia na podstawie prędkości
        btnFromSpeed.setOnClickListener(v -> {
            String speedStr = etSpeed.getText().toString().trim();
            if (speedStr.isEmpty()) {
                Toast.makeText(this, "Podaj prędkość w km/h", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double speed = Double.parseDouble(speedStr);
                if (speed <= 0) {
                    Toast.makeText(this, "Prędkość musi być > 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                double pace = 60.0 / speed; // min/km

                StringBuilder sb = new StringBuilder();
                sb.append(String.format("Tempo: %.2f min/km\n", pace));
                sb.append("Czas na maraton: ").append(formatTime(pace * 42.195)).append("\n");
                sb.append("Czas na półmaraton: ").append(formatTime(pace * 21.0975)).append("\n");

                String distStr = etDistance.getText().toString().trim();
                if (!distStr.isEmpty()) {
                    double dist = Double.parseDouble(distStr);
                    sb.append("Czas na ").append(dist).append(" km: ").append(formatTime(pace * dist)).append("\n");
                }

                tvResult.setText(sb.toString());

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Nieprawidłowa wartość prędkości", Toast.LENGTH_SHORT).show();
            }
        });

        // Obliczenia potrzebnego tempa i prędkości dla zadanego dystansu i czasu
        btnFromTarget.setOnClickListener(v -> {
            String distStr = etTargetDistance.getText().toString().trim();
            String timeStr = etTargetTime.getText().toString().trim();

            if (distStr.isEmpty() || timeStr.isEmpty()) {
                Toast.makeText(this, "Podaj dystans i czas (w minutach)", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double dist = Double.parseDouble(distStr); // km
                double time = Double.parseDouble(timeStr); // minuty

                if (dist <= 0 || time <= 0) {
                    Toast.makeText(this, "Dystans i czas muszą być > 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                double pace = time / dist; // min/km
                double speed = 60.0 / pace; // km/h

                tvResult.setText(String.format("Wymagane tempo: %.2f min/km\nWymagana prędkość: %.2f km/h",
                        pace, speed));

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Nieprawidłowa wartość", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Funkcja pomocnicza do formatowania czasu w hh:mm:ss
    private String formatTime(double minutes) {
        int totalSeconds = (int) Math.round(minutes * 60);
        int h = totalSeconds / 3600;
        int m = (totalSeconds % 3600) / 60;
        int s = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", h, m, s);
    }
}