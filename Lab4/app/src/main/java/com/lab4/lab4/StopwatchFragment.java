package com.lab4.lab4;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StopwatchFragment extends Fragment {

    private TextView sw1, sw2;
    private Button start1, stop1, reset1, start2, stop2, reset2;
    private Handler handler = new Handler();

    private long startTime1 = 0L, timeBuff1 = 0L;
    private boolean running1 = false;

    private long startTime2 = 0L, timeBuff2 = 0L;
    private boolean running2 = false;

    private Runnable updateTimer1 = new Runnable() {
        @Override
        public void run() {
            long updateTime = timeBuff1 + (SystemClock.elapsedRealtime() - startTime1);
            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int millis = (int) (updateTime % 1000) / 10;
            sw1.setText(String.format("%02d:%02d.%02d", mins, secs, millis));
            handler.postDelayed(this, 10);
        }
    };

    private Runnable updateTimer2 = new Runnable() {
        @Override
        public void run() {
            long updateTime = timeBuff2 + (SystemClock.elapsedRealtime() - startTime2);
            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int millis = (int) (updateTime % 1000) / 10;
            sw2.setText(String.format("%02d:%02d.%02d", mins, secs, millis));
            handler.postDelayed(this, 10);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        sw1 = view.findViewById(R.id.time1);
        start1 = view.findViewById(R.id.start1);
        stop1 = view.findViewById(R.id.stop1);
        reset1 = view.findViewById(R.id.reset1);

        sw2 = view.findViewById(R.id.time2);
        start2 = view.findViewById(R.id.start2);
        stop2 = view.findViewById(R.id.stop2);
        reset2 = view.findViewById(R.id.reset2);

        start1.setOnClickListener(v -> {
            if (!running1) {
                startTime1 = SystemClock.elapsedRealtime();
                handler.post(updateTimer1);
                running1 = true;
            }
        });

        stop1.setOnClickListener(v -> {
            if (running1) {
                timeBuff1 += SystemClock.elapsedRealtime() - startTime1;
                handler.removeCallbacks(updateTimer1);
                running1 = false;
            }
        });

        reset1.setOnClickListener(v -> {
            timeBuff1 = 0L;
            sw1.setText("00:00.00");
        });

        start2.setOnClickListener(v -> {
            if (!running2) {
                startTime2 = SystemClock.elapsedRealtime();
                handler.post(updateTimer2);
                running2 = true;
            }
        });

        stop2.setOnClickListener(v -> {
            if (running2) {
                timeBuff2 += SystemClock.elapsedRealtime() - startTime2;
                handler.removeCallbacks(updateTimer2);
                running2 = false;
            }
        });

        reset2.setOnClickListener(v -> {
            timeBuff2 = 0L;
            sw2.setText("00:00.00");
        });

        return view;
    }
}