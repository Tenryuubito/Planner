package com.example.planner.graphics.customView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.planner.R;

public class Divider {
    public static View getDivider(Context context) {
        View view = new View(context);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        param.setMargins(16, 8, 8, 16);

        view.setLayoutParams(param);
        view.setMinimumHeight(1);
        view.setBackgroundColor(Color.BLACK);

        return view;
    }
}
