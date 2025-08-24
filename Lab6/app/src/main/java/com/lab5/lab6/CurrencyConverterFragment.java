package com.lab5.lab6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CurrencyConverterFragment extends Fragment {

    private Spinner spinnerFrom, spinnerTo;
    private EditText amountInput;
    private Button convertBtn;
    private TextView resultView;

    // Kursy walut względem PLN
    private final double USD = 4.0;
    private final double EUR = 4.5;
    private final double GBP = 5.2;
    private final double UAH = 0.11;
    private final double PLN = 1.0;

    private String[] currencies = {"PLN", "USD", "EUR", "GBP", "UAH"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Ładowanie layoutu
        View view = inflater.inflate(R.layout.fragment_currency_converter, container, false);

        // Powiązanie widoków
        spinnerFrom = view.findViewById(R.id.spinnerFrom);
        spinnerTo = view.findViewById(R.id.spinnerTo);
        amountInput = view.findViewById(R.id.amountInput);
        convertBtn = view.findViewById(R.id.convertBtn);
        resultView = view.findViewById(R.id.resultView);

        // Adapter dla spinnerów
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                currencies
        );

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        // Obsługa przycisku
        convertBtn.setOnClickListener(v -> {
            String amountStr = amountInput.getText().toString();
            if (amountStr.isEmpty()) {
                resultView.setText("Podaj kwotę!");
                return;
            }

            double amount = Double.parseDouble(amountStr);
            String from = spinnerFrom.getSelectedItem().toString();
            String to = spinnerTo.getSelectedItem().toString();

            double result = convertCurrency(amount, from, to);
            resultView.setText(amount + " " + from + " = " + result + " " + to);
        });

        return view;
    }

    private double convertCurrency(double amount, String from, String to) {
        double fromRate = getRate(from);
        double toRate = getRate(to);
        return Math.round((amount * fromRate / toRate) * 100.0) / 100.0; // zaokrąglenie do 2 miejsc
    }

    private double getRate(String currency) {
        switch (currency) {
            case "USD": return USD;
            case "EUR": return EUR;
            case "GBP": return GBP;
            case "UAH": return UAH;
            case "PLN": return PLN;
            default: return 1.0;
        }
    }
}