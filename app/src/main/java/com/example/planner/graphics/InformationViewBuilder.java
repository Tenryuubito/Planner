package com.example.planner.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.planner.R;
import com.example.planner.db.entity.Meeting;
import com.example.planner.db.entity.Payment;
import com.example.planner.graphics.customView.CustomLinearLayout;
import com.example.planner.graphics.customView.CustomTextView;
import com.example.planner.graphics.customView.Divider;
import com.example.planner.interfaceCollection.IFormat;
import com.example.planner.interfaceCollection.IMessage;

import java.util.List;

public class InformationViewBuilder {
    public static TextView BuildSelectedDate(String date, Context context) {
        return CustomTextView.getTextView(context, date, CustomTextView.TextType.DATE);
    }

    public static LinearLayout BuildMeetingData(List<Meeting> meetings, Context context, String date) {
        LinearLayout view = CustomLinearLayout.getLinearLayout(context, Color.TRANSPARENT, Color.BLACK);
        boolean isEmpty = true;

        for (Meeting meeting : meetings) {
            if (meeting.date.equals(date)) {
                view.addView(getMeetingView(meeting.title, meeting.information, context));
                isEmpty = false;
            }
        }

        view.addView(Divider.getDivider(context));

        if (isEmpty) return new LinearLayout(context);
        return view;
    }

    public static LinearLayout BuildPaymentData(List<Payment> payments, Context context, String date) {
        LinearLayout view = CustomLinearLayout.getLinearLayout(context, Color.TRANSPARENT, Color.BLACK);
        boolean isEmpty = true;

        view.addView(getPaymentView(context, payments, date));

        for (Payment payment : payments) {
            if (payment.date.equals(date)) {
                view.addView(getPaymentView(payment.title, payment.amount, context));
                isEmpty = false;
            }
        }

        view.addView(Divider.getDivider(context));

        if (isEmpty) return new LinearLayout(context);
        return view;
    }

    private static LinearLayout getMeetingView(String title, String information, Context context) {
        LinearLayout view = CustomLinearLayout.getLinearLayout(context);

        view.addView(getTitleView(title, context));
        view.addView(getInformationView(information, context));

        return view;
    }

    private static TextView getInformationView(String information, Context context) {
        return CustomTextView.getTextView(context, information, CustomTextView.TextType.TEXT);
    }

    private static LinearLayout getPaymentView(String title, float amount, Context context) {
        LinearLayout view = CustomLinearLayout.getLinearLayout(context);

        view.addView(getTitleView(title, context));
        view.addView(getPaymentView(amount, context));

        return view;
    }

    @SuppressLint("DefaultLocale")
    private static TextView getPaymentView(float amount, Context context) {
        return CustomTextView.getTextView(context, String.format("%.2f", amount), CustomTextView.TextType.MONEY);
    }

    @SuppressLint("DefaultLocale")
    private static TextView getPaymentView(Context context, List<Payment> payments, String date) {
        float amount = 0;

        for (Payment payment : payments) {
            if (IFormat.DateInInt(payment.date) <= IFormat.DateInInt(date)) {
                amount += payment.isPayment ? -payment.amount : payment.amount;
            }
        }

        return CustomTextView.getTextView(context, String.format("%.2f", amount), CustomTextView.TextType.MONEY);
    }

    private static TextView getTitleView(String title, Context context) {
        return CustomTextView.getTextView(context, title, CustomTextView.TextType.TITLE);
    }

}
