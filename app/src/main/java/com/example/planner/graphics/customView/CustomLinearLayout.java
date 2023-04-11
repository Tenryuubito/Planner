package com.example.planner.graphics.customView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

public class CustomLinearLayout {
    public static LinearLayout getLinearLayout(Context context, int backgroundColor, int borderColor) {
        LinearLayout view = new LinearLayout(context);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        GradientDrawable border = new GradientDrawable();

        border.setColor(backgroundColor);
        border.setStroke(3, borderColor);

        param.setMargins(8, 16, 8, 16);

        view.setLayoutParams(param);
        view.setBackground(border);

        view.setOrientation(LinearLayout.VERTICAL);

        return view;
    }

    public static LinearLayout getLinearLayout(Context context) {
        LinearLayout view = new LinearLayout(context);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        GradientDrawable border = new GradientDrawable();

        border.setColor(Color.WHITE);
        border.setStroke(3, Color.BLACK);

        param.setMargins(8, 16, 8, 16);

        view.setLayoutParams(param);
        view.setBackground(border);

        view.setOrientation(LinearLayout.VERTICAL);

        return view;
    }
}
