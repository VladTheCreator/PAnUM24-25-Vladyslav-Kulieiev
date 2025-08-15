package com.lab5.lab5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DrinkFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drink, container, false);

        TextView tvDetails = view.findViewById(R.id.tvDrinkDetails);

        Button btnCoke = view.findViewById(R.id.btnCoke);
        Button btnPepsi = view.findViewById(R.id.btnPepsi);

        btnCoke.setOnClickListener(v -> tvDetails.setText("Coca-Cola\nCena: 8 PLN\nOrzeźwiający napój gazowany."));
        btnPepsi.setOnClickListener(v -> tvDetails.setText("Pepsi\nCena: 8 PLN\nOrzeźwiający napój gazowany."));

        return view;
    }
}
