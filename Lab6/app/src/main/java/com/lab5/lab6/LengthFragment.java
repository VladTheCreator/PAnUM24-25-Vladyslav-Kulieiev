package com.lab5.lab6;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class LengthFragment extends Fragment {

    private EditText etValue;
    private Spinner spFrom, spTo;
    private TextView tvOut;

    // współczynniki do METRÓW
    private final Map<String, Double> toMeter = new LinkedHashMap<String, Double>(){{
        put("mm", 0.001);
        put("cm", 0.01);
        put("in", 0.0254);
        put("ft", 0.3048);
        put("yd", 0.9144);
        put("m", 1.0);
        put("km", 1000.0);
    }};

    @Nullable @Override
    public View onCreateView(LayoutInflater inf, @Nullable ViewGroup parent, @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_length, parent, false);
        etValue = v.findViewById(R.id.etValue);
        spFrom = v.findViewById(R.id.spFrom);
        spTo = v.findViewById(R.id.spTo);
        tvOut = v.findViewById(R.id.tvResult);
        Button btn = v.findViewById(R.id.btnCalc);

        ArrayAdapter<String> ad = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, toMeter.keySet().toArray(new String[0]));
        spFrom.setAdapter(ad); spTo.setAdapter(ad);

        btn.setOnClickListener(vw -> convert());
        return v;
    }

    private void convert() {
        String in = etValue.getText().toString().trim();
        if (TextUtils.isEmpty(in)) { toast("Podaj wartość"); return; }
        try {
            double val = Double.parseDouble(in);
            String from = spFrom.getSelectedItem().toString();
            String to   = spTo.getSelectedItem().toString();

            double meters = val * toMeter.get(from);
            double result = meters / toMeter.get(to);
            tvOut.setText(String.format("%s %s = %.6f %s", in, from, result, to));
        } catch (Exception e) { toast("Błąd przeliczenia"); }
    }

    private void toast(String m){ Toast.makeText(requireContext(), m, Toast.LENGTH_SHORT).show(); }
}
