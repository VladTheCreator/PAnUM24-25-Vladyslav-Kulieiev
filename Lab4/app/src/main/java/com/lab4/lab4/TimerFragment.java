package com.lab4.lab4;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimerFragment extends Fragment{
    private TextView timer1, timer2;
    private Button set1, start1, stop1, set2, start2, stop2;
    private CountDownTimer cdTimer1, cdTimer2;
    private long timeLeft1 = 0, timeLeft2 = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        timer1 = view.findViewById(R.id.timer1);
        set1 = view.findViewById(R.id.set1);
        start1 = view.findViewById(R.id.startTimer1);
        stop1 = view.findViewById(R.id.stopTimer1);

        timer2 = view.findViewById(R.id.timer2);
        set2 = view.findViewById(R.id.set2);
        start2 = view.findViewById(R.id.startTimer2);
        stop2 = view.findViewById(R.id.stopTimer2);

        set1.setOnClickListener(v -> pickTime(1));
        start1.setOnClickListener(v -> startTimer(1));
        stop1.setOnClickListener(v -> stopTimer(1));

        set2.setOnClickListener(v -> pickTime(2));
        start2.setOnClickListener(v -> startTimer(2));
        stop2.setOnClickListener(v -> stopTimer(2));

        return view;
    }

    private void pickTime(int which) {
        TimePickerDialog tpd = new TimePickerDialog(getContext(), (view, minute, second) -> {
            long millis = (minute * 60L + second) * 1000;
            if (which == 1) {
                timeLeft1 = millis;
                timer1.setText(String.format("%02d:%02d", minute, second));
            } else {
                timeLeft2 = millis;
                timer2.setText(String.format("%02d:%02d", minute, second));
            }
        }, 0, 0, true);
        tpd.show();
    }

    private void startTimer(int which) {
        if (which == 1 && timeLeft1 > 0) {
            cdTimer1 = new CountDownTimer(timeLeft1, 1000) {
                public void onTick(long millisUntilFinished) {
                    timeLeft1 = millisUntilFinished;
                    int secs = (int) (millisUntilFinished / 1000);
                    int mins = secs / 60;
                    secs %= 60;
                    timer1.setText(String.format("%02d:%02d", mins, secs));
                }
                public void onFinish() {
                    timer1.setText("00:00");
                }
            }.start();
        } else if (which == 2 && timeLeft2 > 0) {
            cdTimer2 = new CountDownTimer(timeLeft2, 1000) {
                public void onTick(long millisUntilFinished) {
                    timeLeft2 = millisUntilFinished;
                    int secs = (int) (millisUntilFinished / 1000);
                    int mins = secs / 60;
                    secs %= 60;
                    timer2.setText(String.format("%02d:%02d", mins, secs));
                }
                public void onFinish() {
                    timer2.setText("00:00");
                }
            }.start();
        }
    }

    private void stopTimer(int which) {
        if (which == 1 && cdTimer1 != null) cdTimer1.cancel();
        if (which == 2 && cdTimer2 != null) cdTimer2.cancel();
    }
}
