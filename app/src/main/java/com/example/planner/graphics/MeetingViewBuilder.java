package com.example.planner.graphics;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.example.planner.R;
import com.example.planner.graphics.customView.CustomButton;
import com.example.planner.graphics.customView.CustomLinearLayout;
import com.example.planner.graphics.customView.CustomTextView;
import com.example.planner.ui.home.meeting.showMeetings.ShowMeetingsFragment;

public class MeetingViewBuilder {
    public static LinearLayout BuildMeetingView(int id, String title, String date, String information, Context context) {

        LinearLayout view = CustomLinearLayout.getLinearLayout(context);

        view.addView(getTitleView(title, context));
        view.addView(getDateView(date, context));
        view.addView(getInformationView(information, context));
        view.addView(getEditButton(id, context));

        return view;
    }

    private static TextView getTitleView(String title, Context context) {
        return CustomTextView.getTextView(context, title, CustomTextView.TextType.TITLE);
    }

    private static TextView getDateView(String date, Context context) {
        return CustomTextView.getTextView(context, date, CustomTextView.TextType.DATE);
    }

    private static TextView getInformationView(String information, Context context) {
        return CustomTextView.getTextView(context, information, CustomTextView.TextType.TEXT);
    }

    private static Button getEditButton(int id, Context context) {
        return CustomButton.getButton(context, context.getResources().getString(R.string.Meeting_Button_Edit_Text), MeetingViewBuilder::EditMeeting, id);
    }

    private static void EditMeeting(View view, int id) {
        Navigation.findNavController(view).navigate(R.id.nav_editmeeting);
        ShowMeetingsFragment.selectedMeetingID = id;
    }

}
