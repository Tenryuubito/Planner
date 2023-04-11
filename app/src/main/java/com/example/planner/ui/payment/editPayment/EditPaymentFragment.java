package com.example.planner.ui.payment.editPayment;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.planner.MainActivity;
import com.example.planner.R;
import com.example.planner.db.base.MeetingDataBase;
import com.example.planner.db.base.PaymentDataBase;
import com.example.planner.db.entity.Meeting;
import com.example.planner.db.entity.Payment;
import com.example.planner.db.iConnection.PaymentDao;
import com.example.planner.interfaceCollection.ICloseable;
import com.example.planner.ui.home.meeting.showMeetings.ShowMeetingsFragment;
import com.example.planner.ui.payment.showPayment.ShowPaymentFragment;

public class EditPaymentFragment extends Fragment {

    private View binding;

    private EditText EditText_Title, EditText_Date, EditText_Amount;
    private Payment selectedPayment;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = inflater.inflate(R.layout.fragment_payment_editpayment, container, false);
        MainActivity.setActiveView(binding);

        Button Button_Save = binding.findViewById(R.id.EditPayment_Button_Save);
        Button Button_Delete = binding.findViewById(R.id.EditPayment_Button_Delete);

        EditText_Title = binding.findViewById(R.id.EditPayment_EditText_Title);
        EditText_Date = binding.findViewById(R.id.EditPayment_EditText_Date);
        EditText_Amount = binding.findViewById(R.id.EditPayment_EditText_Amount);

        LoadPaymentData();

        Button_Save.setOnClickListener(this::SavesChanges);
        Button_Delete.setOnClickListener(this::DeletePayment);

        return binding;
    }

    private void SavesChanges(View view) {
        Payment.db_updatePayment(selectedPayment.id, EditText_Title.getText().toString(), EditText_Date.getText().toString(), Float.parseFloat(EditText_Amount.getText().toString()), selectedPayment.isPayment, getContext());

        ICloseable.CloseKeyboard(this);
    }

    private void DeletePayment(View view) {
        Payment.db_deletePayment(selectedPayment, getContext());
        ICloseable.CloseKeyboard(this);
        Navigation.findNavController(view).navigate(R.id.nav_payment);
    }

    private void LoadPaymentData() {
        for (Payment paymentSearch : Payment.db_getAllPayments(getContext())) {
            if (paymentSearch.id == ShowPaymentFragment.selectedPaymentID) {
                selectedPayment = paymentSearch;
                break;
            }
        }

        EditText_Title.setText(selectedPayment.title);
        EditText_Date.setText(selectedPayment.date);
        EditText_Amount.setText(String.valueOf(selectedPayment.amount));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ICloseable.CloseKeyboard(this);

        binding = null;
    }

}