package com.lab5.lab6;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class AreaFragment extends Fragment {

    private EditText etValue;
    private Spinner spFrom, spTo;
    private TextView tvOut;

    // współczynniki do METRÓW KWADRATOWYCH
    private final Map<String, Double> toM2 = new LinkedHashMap<String, Double>(){{
        put("mm2", 1e-6);
        put("cm2", 1e-4);
        put("m2", 1.0);
        put("km2", 1e6);
        put("a", 100.0);     // 1 ar = 100 m2
        put("ha", 10000.0);  // 1 ha = 10000 m2
    }};

    @Nullable @Override
    public View onCreateView(LayoutInflater inf, @Nullable ViewGroup parent, @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_area, parent, false);
        etValue = v.findViewById(R.id.etValue);
        spFrom = v.findViewById(R.id.spFrom);
        spTo = v.findViewById(R.id.spTo);
        tvOut = v.findViewById(R.id.tvResult);
        Button btn = v.findViewById(R.id.btnCalc);

        ArrayAdapter<String> ad = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, toM2.keySet().toArray(new String[0]));
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

            double m2 = val * toM2.get(from);
            double result = m2 / toM2.get(to);
            tvOut.setText(String.format("%s %s = %.6f %s", in, from, result, to));
        } catch (Exception e) { toast("Błąd przeliczenia"); }
    }

    private void toast(String m){ Toast.makeText(requireContext(), m, Toast.LENGTH_SHORT).show(); }
}