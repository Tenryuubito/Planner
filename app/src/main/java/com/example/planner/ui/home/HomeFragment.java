package com.example.planner.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.planner.MainActivity;
import com.example.planner.R;
import com.example.planner.db.entity.Meeting;
import com.example.planner.graphics.MeetingViewBuilder;
import com.example.planner.graphics.customView.CustomButton;
import com.example.planner.interfaceCollection.IFormat;
import com.example.planner.interfaceCollection.INavigable;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class HomeFragment extends Fragment {

    public static String selectedDate;

    private View binding;

    private CalendarView CalendarView_Calendar;


    @SuppressLint("DefaultLocale")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = inflater.inflate(R.layout.fragment_home, container, false);
        MainActivity.setActiveView(binding);

        Button Button_AddNewMeeting = binding.findViewById(R.id.Home_Button_AddNewMeeting);
        Button Button_AddNewPayment = binding.findViewById(R.id.Home_Button_AddNewPayment);
        Button Button_RemovePayment = binding.findViewById(R.id.Home_Button_RemovePayment);
        Button Button_OpenMeeting = binding.findViewById(R.id.Home_Button_OpenMeeting);

        CalendarView_Calendar = binding.findViewById(R.id.Home_CalendarView_Calendar);

        selectedDate = IFormat.CurrentDate();

        Button_AddNewMeeting.setOnClickListener(this::AddNewMeeting);
        Button_AddNewPayment.setOnClickListener(this::AddNewPayment);
        Button_RemovePayment.setOnClickListener(this::RemovePayment);
        Button_OpenMeeting.setOnClickListener(this::OpenMeeting);

        if (ShowNextMeetings()) ShowTitle();
        CheckDateIsSelected();
        CheckMeetingsToDelete();

        CalendarView_Calendar.setOnDateChangeListener(this::SetChosenDate);

        return binding;
    }

    private boolean ShowNextMeetings() {
        LinearLayout nextMeetings = binding.findViewById(R.id.Home_LinearLayout_ShowNextMeetings);
        List<Meeting> nextMeetingsList = Meeting.db_getAllMeetings(getContext());
        boolean isNotEmpty = false;

        Collections.sort(nextMeetingsList);

        for (Meeting meeting : nextMeetingsList) {
            int dateDif = Meeting.getDateDif(meeting);
            if (dateDif >= 0 && dateDif <= 7) {
                nextMeetings.addView(MeetingViewBuilder.BuildMeetingView(meeting.id, meeting.title, meeting.date, meeting.information, getContext()));
                isNotEmpty = true;
            }
        }

        return isNotEmpty;
    }

    private void ShowDate(View view) {
        INavigable.Navigate(view, R.id.nav_dateInformation);

    }

    private void ShowTitle() {
        TextView TextView_Title = binding.findViewById(R.id.Home_TextView_ShowMeetingsTitle);
        TextView_Title.setVisibility(View.VISIBLE);
    }

    private void AddNewMeeting(View view) {
        INavigable.Navigate(view, R.id.nav_addmeeting);
    }

    private void AddNewPayment(View view) {
        Navigation.findNavController(view).navigate(R.id.nav_addpayment);
    }

    private void RemovePayment(View view) {
    }

    private void OpenMeeting(View view) {
        Navigation.findNavController(view).navigate(R.id.nav_showmeeting);
    }

    private void CheckDateIsSelected() {
        if (!HomeFragment.selectedDate.equals("")) {

        }
    }

    @SuppressLint("DefaultLocale")
    private void SetChosenDate(View view, int year, int month, int day) {
        selectedDate = IFormat.Date(day, month + 1, year);
        ShowDate(view);
    }

    private void CheckMeetingsToDelete() {
        for (Meeting meeting : Meeting.db_getAllMeetings(getContext())) {
            if (Meeting.getDateDif(meeting) > 180) {
                Meeting.db_deleteMeeting(meeting, getContext());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}