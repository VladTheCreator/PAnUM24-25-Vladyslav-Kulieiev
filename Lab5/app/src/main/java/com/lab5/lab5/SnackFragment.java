package com.lab5.lab5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SnackFragment extends Fragment {

    private TextView textViewSnackDetails;

    public SnackFragment() {
        // Wymagany pusty konstruktor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflatuj layout fragmentu
        View view = inflater.inflate(R.layout.fragment_snack, container, false);

        // Znajdź TextView i przyciski
        textViewSnackDetails = view.findViewById(R.id.textViewSnackDetails);

        Button buttonSnack1 = view.findViewById(R.id.buttonSnack1);
        Button buttonSnack2 = view.findViewById(R.id.buttonSnack2);
        Button buttonSnack3 = view.findViewById(R.id.buttonSnack3);

        // Obsługa kliknięć
        buttonSnack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewSnackDetails.setText("Chipsy – chrupiące, słone. Cena: 5 zł");
            }
        });

        buttonSnack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewSnackDetails.setText("Ciastko czekoladowe – słodkie, świeże. Cena: 3 zł");
            }
        });

        buttonSnack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewSnackDetails.setText("Orzechy mieszane – zdrowa przekąska. Cena: 6 zł");
            }
        });

        return view;
    }
}
