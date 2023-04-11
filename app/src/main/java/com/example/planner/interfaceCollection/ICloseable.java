package com.example.planner.interfaceCollection;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

import java.util.Objects;

public interface ICloseable {
    static void CloseKeyboard(Fragment fragment) {
        View view = fragment.requireActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) fragment.requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
