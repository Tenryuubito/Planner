package com.example.planner.interfaceCollection;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

public interface ICheck {
    static String checkDate(String date) {
        String format = "dd.MM.yyyy";

        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            sdf.parse(date);
            return date;
        } catch (Exception e) {
            return "";
        }
    }

    @SuppressLint("DefaultLocale")
    static String checkPay(String pay) {
        if (pay.matches("")) return pay;
        return "";


    }
}
