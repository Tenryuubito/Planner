package com.example.planner.ui.menu.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.planner.MainActivity;
import com.example.planner.R;
import com.example.planner.db.entity.Meeting;
import com.example.planner.db.entity.Payment;
import com.example.planner.interfaceCollection.IFormat;
import com.example.planner.interfaceCollection.IMessage;


public class SettingsFragment extends Fragment {

    private View binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = inflater.inflate(R.layout.fragment_menu_settings, container, false);
        MainActivity.setActiveView(binding);

        Button Button_ResetData = binding.findViewById(R.id.SettingsFragment_Button_ResetData);
        Button Button_AddTestData = binding.findViewById(R.id.SettingsFragment_Button_AddTestData);

        Button_ResetData.setOnClickListener(this::ResetData);
        Button_AddTestData.setOnClickListener(this::AddTestData);

        return binding;
    }

    private void ResetData(View view) {
        Payment.db_deleteAll(getContext());
        Meeting.db_deleteAll(getContext());
    }

    private void AddTestData(View view) {
       for (int loop = 0; loop < 10; loop++) {
            Meeting.db_addMeeting(getRandomMeeting(loop), getContext());
            Payment.db_addPayment(getRandomPayment(loop), getContext());
        }
    }

    private Meeting getRandomMeeting(int id) {
        return new Meeting("title" + id, IFormat.Date((int)(Math.random()*30 + 1), (int)(Math.random()*12 + 1), 2023), "information" + id);
    }

    private Payment getRandomPayment(int id) {
        return new Payment("title" + id, IFormat.Date((int)(Math.random()*30 + 1), (int)(Math.random()*12 + 1), 2023), (float)Math.random()*(100), (int)(Math.random() + 0.5) == 0);
    }
}
