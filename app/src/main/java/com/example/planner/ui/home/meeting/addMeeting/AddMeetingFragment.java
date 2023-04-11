package com.example.planner.ui.home.meeting.addMeeting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.planner.MainActivity;
import com.example.planner.R;
import com.example.planner.db.base.MeetingDataBase;
import com.example.planner.db.entity.Meeting;
import com.example.planner.db.iConnection.MeetingDao;
import com.example.planner.interfaceCollection.ICheck;
import com.example.planner.interfaceCollection.ICloseable;
import com.example.planner.interfaceCollection.INavigable;
import com.example.planner.ui.home.HomeFragment;

public class AddMeetingFragment extends Fragment {
    private View binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = inflater.inflate(R.layout.fragment_home_addmeeting, container, false);
        MainActivity.setActiveView(binding);

        Button Button_AddMeeting = binding.findViewById(R.id.AddMeeting_Button_AddNewMeeting);

        ((EditText)binding.findViewById(R.id.AddMeeting_EditText_Date)).setText(HomeFragment.selectedDate);

        Button_AddMeeting.setOnClickListener(this::AddMeeting);

        return binding;
    }

    private void AddMeeting(View view) {
        String title = ((EditText)binding.findViewById(R.id.AddMeeting_EditText_Title)).getText().toString();
        String date = ((EditText)binding.findViewById(R.id.AddMeeting_EditText_Date)).getText().toString();
        String information = ((EditText)binding.findViewById(R.id.AddMeeting_EditText_Information)).getText().toString();

        date = ICheck.checkDate(date);

        Meeting.db_addMeeting(new Meeting(title, date, information), getContext());

        INavigable.Navigate(view, R.id.nav_home);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ICloseable.CloseKeyboard(this);

        binding = null;
    }
}
