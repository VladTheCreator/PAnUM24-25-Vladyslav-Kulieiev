package com.lab5.lab5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LocationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        TextView tvLocation = view.findViewById(R.id.tvLocationInfo);
        tvLocation.setText(
                "Nazwa: Restauracja XYZ\n" +
                        "Adres: ul. Przykładowa 10, 00-001 Warszawa\n" +
                        "Godziny otwarcia: 10:00 - 22:00"
        );

        return view;
    }
}
