package com.example.planner.interfaceCollection;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.Objects;

public interface INavigable {
    static void Navigate(View view, int navID) {
        Navigation.findNavController(view).navigate(navID);

    }
}
