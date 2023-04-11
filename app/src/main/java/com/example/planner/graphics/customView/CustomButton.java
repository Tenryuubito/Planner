package com.example.planner.graphics.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.planner.R;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CustomButton {
    @SuppressLint("UseCompatLoadingForDrawables")
    public static Button getButton(Context context, String content, BiConsumer<View, Integer> action, int id) {
        Button view = new Button(context);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        param.setMargins(32, 16, 32, 16);

        view.setLayoutParams(param);
        view.setBackground(context.getDrawable(R.drawable.custom_button));
        view.setText(content);
        view.setOnClickListener(view1 -> action.accept(view1, id));

        return view;
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public static Button getButton(Context context, String content, Consumer<View> action) {
        Button view = new Button(context);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        param.setMargins(32, 16, 32, 16);

        view.setLayoutParams(param);
        view.setBackground(context.getDrawable(R.drawable.custom_button));
        view.setText(content);
        view.setOnClickListener(action::accept);

        return view;
    }

}
