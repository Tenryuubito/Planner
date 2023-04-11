package com.example.planner.ui.home.meeting.editMeeting;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import com.example.planner.interfaceCollection.ICloseable;
import com.example.planner.interfaceCollection.INavigable;
import com.example.planner.ui.home.meeting.showMeetings.ShowMeetingsFragment;

public class EditMeetingFragment extends Fragment {

    private View binding;

    private EditText EditText_Title;
    private EditText EditText_Date;
    private EditText EditText_Information;

    private Meeting selectedMeeting;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = inflater.inflate(R.layout.fragment_home_editmeeting, container, false);
        MainActivity.setActiveView(binding);

        Button Button_Edit = binding.findViewById(R.id.EditMeeting_Button_Save);
        Button Button_Delete = binding.findViewById(R.id.EditMeeting_Button_Delete);

        EditText_Title = binding.findViewById(R.id.EditMeeting_EditText_Title);
        EditText_Date = binding.findViewById(R.id.EditMeeting_EditText_Date);
        EditText_Information = binding.findViewById(R.id.EditMeeting_EditText_Information);

        LoadMeetingData();

        Button_Edit.setOnClickListener(this::SaveChanges);
        Button_Delete.setOnClickListener(this::DeleteMeeting);

        return binding;
    }

    private void SaveChanges(View view) {
        Meeting.db_updateMeeting(selectedMeeting.id, EditText_Title.getText().toString(), EditText_Date.getText().toString(), EditText_Information.getText().toString(), getContext());

        ICloseable.CloseKeyboard(this);
    }

    private void DeleteMeeting(View view) {
        Meeting.db_deleteMeeting(selectedMeeting, getContext());
        ICloseable.CloseKeyboard(this);
        INavigable.Navigate(view, R.id.nav_home);

    }

    private void LoadMeetingData() {
        for (Meeting meetingSearch : Meeting.db_getAllMeetings(getContext())) {
            if (meetingSearch.id == ShowMeetingsFragment.selectedMeetingID) {
                selectedMeeting = meetingSearch;
                break;
            }
        }

        EditText_Title.setText(selectedMeeting.title);
        EditText_Date.setText(selectedMeeting.date);
        EditText_Information.setText(selectedMeeting.information);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ICloseable.CloseKeyboard(this);

        binding = null;
    }
}
