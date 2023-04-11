package com.example.planner.interfaceCollection;

import android.annotation.SuppressLint;

import com.example.planner.ui.home.HomeFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public interface IFormat {
    @SuppressLint("DefaultLocale")
    static String CurrentDate() {
        LocalDate today = LocalDate.now();
        String dayText = today.getDayOfMonth() < 10 ? "0" + today.getDayOfMonth() : Integer.toString(today.getDayOfMonth());
        String monthText = today.getMonthValue() < 10 ? "0" + today.getMonthValue() : Integer.toString(today.getMonth().getValue() + 1);
        return String.format("%s.%s.%d", dayText, monthText, today.getYear());
    }

    static String Month(String date) {
        return date.split("\\.")[1];
    }

    static String MonthAndYear(String date) {
        return String.format("%s.%s", date.split("\\.")[1], date.split("\\.")[2]);
    }

    @SuppressLint("DefaultLocale")
    static String Date(int day, int month, int year) {
        String day_s = day < 10 ? "0" + day : Integer.toString(day);
        String month_s = month < 10 ? "0" + month : Integer.toString(month);
        return String.format("%s.%s.%d", day_s, month_s, year);
    }

    static int DateInInt(String date) {
        String[] val = date.split("\\.");
        String convert = val[2] + val[1] + val[0];
        return Integer.parseInt(convert);
    }
}
