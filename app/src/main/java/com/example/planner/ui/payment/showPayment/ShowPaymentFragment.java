package com.example.planner.ui.payment.showPayment;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import com.example.planner.db.base.PaymentDataBase;
import com.example.planner.db.entity.Payment;
import com.example.planner.db.iConnection.PaymentDao;
import com.example.planner.graphics.PaymentViewBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ShowPaymentFragment extends Fragment {

    public static int selectedPaymentID;
    private View binding;
    private LinearLayout LinearLayout_Payments;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = inflater.inflate(R.layout.fragment_payment_showpayment, container, false);
        MainActivity.setActiveView(binding);

        LinearLayout_Payments = binding.findViewById(R.id.ShowPayment_LinearLayout_ShowPayments);

        LoadPayments();

        return binding;
    }

    private void LoadPayments() {
        List<Payment> payments = Payment.db_getAllPayments(getContext());
        Collections.sort(payments);

        for (Payment payment : payments) {
            LinearLayout_Payments.addView(PaymentViewBuilder.BuildPaymentView(payment.id, payment.title, payment.date, payment.amount, getContext()));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}