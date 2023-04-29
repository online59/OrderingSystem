package com.example.orderingsystem.view.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.FragmentReportBinding;

public class ReportFragment extends Fragment {

    private FragmentReportBinding binding;
    private static ReportFragment instance;

    private ReportFragment() {
    }

    public static ReportFragment getInstance() {
        if (instance == null) {
            instance = new ReportFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReportBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}