package com.example.planner.ui.payment.addPayment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.example.planner.MainActivity;
import com.example.planner.R;
import com.example.planner.db.base.MeetingDataBase;
import com.example.planner.db.base.PaymentDataBase;
import com.example.planner.db.entity.Payment;
import com.example.planner.db.iConnection.PaymentDao;
import com.example.planner.interfaceCollection.ICloseable;

public class AddPaymentFragment extends Fragment {

    private View binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = inflater.inflate(R.layout.fragment_payment_addpayment, container, false);
        MainActivity.setActiveView(binding);

        Button Button_AddNewPayment = binding.findViewById(R.id.AddPayment_Button_AddNewPayment);

        Button_AddNewPayment.setOnClickListener(this::AddNewPayment);

        return binding;
    }

    private void AddNewPayment(View view) {
        String title = ((EditText)binding.findViewById(R.id.AddPayment_EditText_Title)).getText().toString();
        String date = ((EditText)binding.findViewById(R.id.AddPayment_EditText_Date)).getText().toString();
        String amount = ((EditText)binding.findViewById(R.id.AddPayment_EditText_Amount)).getText().toString().replaceAll(",", ".");
        boolean payment = ((Switch)binding.findViewById(R.id.AddPayment_Switch_PaymentOrNot)).isChecked();

        float amountValue = Float.parseFloat(amount);

        Payment.db_addPayment(new Payment(title, date, amountValue, !payment), getContext());

        Navigation.findNavController(view).navigate(R.id.nav_payment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ICloseable.CloseKeyboard(this);
        binding = null;
    }
}
