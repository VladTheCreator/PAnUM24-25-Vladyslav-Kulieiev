package com.lab5.lab6;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;

public class BaseFragment extends Fragment {

    private EditText etInput;
    private Spinner spFrom;
    private TextView tv2, tv4, tv8, tv10, tv16;

    private final String[] bases = {"2","4","8","10","16"};

    @Nullable @Override
    public View onCreateView(LayoutInflater inf, @Nullable ViewGroup parent, @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_base, parent, false);
        etInput = v.findViewById(R.id.etNumber);
        spFrom = v.findViewById(R.id.spFromBase);
        tv2 = v.findViewById(R.id.tvBase2);
        tv4 = v.findViewById(R.id.tvBase4);
        tv8 = v.findViewById(R.id.tvBase8);
        tv10 = v.findViewById(R.id.tvBase10);
        tv16 = v.findViewById(R.id.tvBase16);
        Button btn = v.findViewById(R.id.btnConvert);

        ArrayAdapter<String> ad = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, bases);
        spFrom.setAdapter(ad);

        btn.setOnClickListener(vw -> convert());
        return v;
    }

    private void convert() {
        String in = etInput.getText().toString().trim();
        if (TextUtils.isEmpty(in)) { toast("Podaj liczbę"); return; }

        int fromBase = Integer.parseInt(spFrom.getSelectedItem().toString());
        try {
            // zamiana wejścia na wartość w systemie 10
            int value = Integer.parseInt(in, fromBase);

            tv2.setText("bin (2): " + Integer.toString(value,2));
            tv4.setText("quart (4): " + toBase(value,4));
            tv8.setText("oct (8): " + Integer.toString(value,8));
            tv10.setText("dec (10): " + value);
            tv16.setText("hex (16): " + Integer.toString(value,16).toUpperCase());
        } catch (Exception e) {
            toast("Nieprawidłowa liczba dla bazy " + fromBase);
        }
    }

    // konwersja do dowolnej bazy 2..36 (tu używamy 4)
    private String toBase(int value, int base) {
        if (value == 0) return "0";
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        int v = Math.abs(value);
        while (v > 0) { sb.append(digits.charAt(v % base)); v /= base; }
        if (value < 0) sb.append('-');
        return sb.reverse().toString();
    }

    private void toast(String m){ Toast.makeText(requireContext(), m, Toast.LENGTH_SHORT).show(); }
}
