package com.lab5.lab5_2;

import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LocationFragment extends Fragment {

    private DatabaseHelper db;
    private LinearLayout layoutButtons;
    private TextView tvInfo;
    private ImageView ivMap;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location, container, false);
        layoutButtons = v.findViewById(R.id.layoutButtons);
        tvInfo = v.findViewById(R.id.tvInfo);
        ivMap = v.findViewById(R.id.ivMap);
        db = new DatabaseHelper(requireContext());
        populate();
        return v;
    }

    private void populate() {
        layoutButtons.removeAllViews();
        Cursor c = db.getLocations();
        int idxName = c.getColumnIndex("name");
        int idxAddr = c.getColumnIndex("address");
        int idxHours = c.getColumnIndex("hours");
        int idxMap = c.getColumnIndex("map_res");

        if (c.moveToFirst()) {
            do {
                final String name = c.getString(idxName);
                final String addr = c.getString(idxAddr);
                final String hours = c.getString(idxHours);
                final String mapRes = c.getString(idxMap);

                Button b = new Button(requireContext());
                b.setText(name);
                b.setOnClickListener(v -> {
                    tvInfo.setText(
                            "Nazwa: " + name + "\n" +
                                    "Adres: " + addr + "\n" +
                                    "Godziny otwarcia: " + hours
                    );
                    // Załaduj statyczną mapę po nazwie zasobu z drawable
                    int resId = getResources().getIdentifier(mapRes, "drawable", requireContext().getPackageName());
                    if (resId != 0) {
                        ivMap.setImageResource(resId);
                    } else {
                        ivMap.setImageResource(R.drawable.map_sample); // fallback
                    }
                });
                layoutButtons.addView(b);
            } while (c.moveToNext());
        }
        c.close();
    }
}
