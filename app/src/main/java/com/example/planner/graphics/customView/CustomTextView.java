package com.example.planner.graphics.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomTextView {
    public enum TextType {
        TITLE,
        DATE,
        MONEY,
        TEXT;

    }
    @SuppressLint("SetTextI18n")
    public static TextView getTextView(Context context, String content, TextType textType) {
        TextView view = new TextView(context);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        param.setMargins(16, 16, 16, 16);
        view.setLayoutParams(param);
        view.setText(content);

        switch (textType) {
            case TITLE:
                setTitleLayout(view);
                break;
            case DATE:
                setDateLayout(view);
                break;
            case TEXT:
                setTextLayout(view);
                break;
            case MONEY:
                setMoneyLayout(view);
                view.setText(content + " €");
                break;
        }

        return view;
    }

    private static void setMoneyLayout(TextView view) {
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        view.setTextSize(16);
        view.setTextColor(Color.BLACK);
    }

    private static void setTitleLayout(TextView view) {
        view.setTextColor(Color.BLACK);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        view.setTextSize(22);
    }

    private static  void setDateLayout(TextView view) {
        view.setTextColor(Color.BLACK);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        view.setTextSize(20);
    }

    private static void setTextLayout(TextView view) {
        view.setTextColor(Color.BLACK);
        view.setTextSize(18);
    }
}
