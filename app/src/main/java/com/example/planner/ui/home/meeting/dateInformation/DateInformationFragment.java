package com.example.planner.ui.home.meeting.dateInformation;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.planner.MainActivity;
import com.example.planner.R;
import com.example.planner.db.base.MeetingDataBase;
import com.example.planner.db.entity.Meeting;
import com.example.planner.db.entity.Payment;
import com.example.planner.interfaceCollection.IMessage;
import com.example.planner.graphics.InformationViewBuilder;
import com.example.planner.ui.home.HomeFragment;

public class DateInformationFragment extends Fragment {

    private View binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = inflater.inflate(R.layout.fragment_home_dateinformation, container, false);
        MainActivity.setActiveView(binding);

        LinearLayout LinearLayout_Information = binding.findViewById(R.id.DateInformation_LinearLayout_BasicInformation);
        LinearLayout LinearLayout_Payments = binding.findViewById(R.id.DateInformation_LinearLayout_Payments);
        LinearLayout LinearLayout_Meetings = binding.findViewById(R.id.DateInformation_LinearLayout_Meetings);

        LinearLayout_Information.addView(InformationViewBuilder.BuildSelectedDate(HomeFragment.selectedDate, getContext()));

        LinearLayout_Payments.addView(LoadPaymentData());
        LinearLayout_Meetings.addView(LoadMeetingData());

        return binding;
    }

    private LinearLayout LoadPaymentData() {
        return InformationViewBuilder.BuildPaymentData(Payment.db_getAllPayments(getContext()), getContext(), HomeFragment.selectedDate);
    }

    private LinearLayout LoadMeetingData() {
        return InformationViewBuilder.BuildMeetingData(Meeting.db_getAllMeetings(getContext()), getContext(), HomeFragment.selectedDate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
