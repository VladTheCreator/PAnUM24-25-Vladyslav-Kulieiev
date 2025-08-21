package com.lab5.lab5_2;

import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SnackFragment extends Fragment {

    private DatabaseHelper db;
    private LinearLayout layoutButtons;
    private TextView tvDetails;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_snack, container, false);
        layoutButtons = v.findViewById(R.id.layoutButtons);
        tvDetails = v.findViewById(R.id.tvDetails);
        db = new DatabaseHelper(requireContext());
        populate();
        return v;
    }

    private void populate() {
        layoutButtons.removeAllViews();
        Cursor c = db.getSnacks();
        int idxName = c.getColumnIndex("name");
        int idxDesc = c.getColumnIndex("description");
        int idxPrice = c.getColumnIndex("price");
        if (c.moveToFirst()) {
            do {
                final String name = c.getString(idxName);
                final String desc = c.getString(idxDesc);
                final double price = c.getDouble(idxPrice);

                Button b = new Button(requireContext());
                b.setText(name);
                b.setOnClickListener(v -> tvDetails.setText(
                        name + "\n" + desc + "\nCena: " + price + " PLN"
                ));
                layoutButtons.addView(b);
            } while (c.moveToNext());
        }
        c.close();
    }
}
