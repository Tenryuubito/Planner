package com.example.planner.ui.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.example.planner.MainActivity;
import com.example.planner.R;
import com.example.planner.db.base.PaymentDataBase;
import com.example.planner.db.entity.Payment;
import com.example.planner.db.iConnection.PaymentDao;
import com.example.planner.graphics.ChartBuilder;

public class PaymentFragment extends Fragment {

    private View binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = inflater.inflate(R.layout.fragment_payment, container, false);
        MainActivity.setActiveView(binding);

        Button Button_AddNewPayment = binding.findViewById(R.id.Payment_Button_AddNewPayment);
        Button Button_OpenPayments = binding.findViewById(R.id.Payment_Button_OpenPayments);

        Button_AddNewPayment.setOnClickListener(this::AddNewPayment);
        Button_OpenPayments.setOnClickListener(this::OpenPayments);

        LoadChart(inflater, container);

        return binding;
    }

    private void LoadChart(@NonNull LayoutInflater inflater, ViewGroup container) {
        LinearLayout frame = binding.findViewById(R.id.Payment_LinearLayout_Chart);
        frame.addView(ChartBuilder.getLineChart(inflater, container, Payment.db_getAllPayments(getContext())));
    }

    private void AddNewPayment(View view) {
        Navigation.findNavController(view).navigate(R.id.nav_addpayment);
    }

    private void OpenPayments(View view) {
        Navigation.findNavController(view).navigate(R.id.nav_showpayment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}