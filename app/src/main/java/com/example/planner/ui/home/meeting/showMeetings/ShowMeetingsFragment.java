package com.example.planner.ui.home.meeting.showMeetings;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.example.planner.MainActivity;
import com.example.planner.R;
import com.example.planner.db.base.MeetingDataBase;
import com.example.planner.db.entity.Meeting;
import com.example.planner.db.iConnection.MeetingDao;
import com.example.planner.graphics.MeetingViewBuilder;
import com.example.planner.interfaceCollection.IFormat;
import com.example.planner.ui.home.HomeFragment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowMeetingsFragment extends Fragment {
    private View binding;
    private LinearLayout LinearLayout_Meetings;

    public static int selectedMeetingID;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = inflater.inflate(R.layout.fragment_home_showmeetings, container, false);
        MainActivity.setActiveView(binding);

        LinearLayout_Meetings = binding.findViewById(R.id.ShowMeetings_LinearLayout_Meetings);

        LoadMeetings();
        CheckMeetingsToDelete();

        return binding;
    }

    private void LoadMeetings() {
        List<Meeting> meetings = Meeting.db_getAllMeetings(getContext());
        Collections.sort(meetings);

        for (Meeting meeting : meetings) {
            LinearLayout_Meetings.addView(MeetingViewBuilder.BuildMeetingView(meeting.id, meeting.title, meeting.date, meeting.information, getContext()));
        }
    }

    private void CheckMeetingsToDelete() {
        Meeting localeDate = new Meeting("", IFormat.CurrentDate(), "");

        for (Meeting meeting : Meeting.db_getAllMeetings(getContext())) {
            if (Meeting.getDateDif(meeting, localeDate) > 30) {
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
