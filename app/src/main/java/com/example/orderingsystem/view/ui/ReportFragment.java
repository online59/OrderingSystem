package com.example.orderingsystem.view.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.orderingsystem.databinding.FragmentReportBinding;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.repository.OrderRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseOrderService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.viewmodel.MainViewModel;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ReportFragment extends Fragment {

    private FragmentReportBinding binding;
    private static ReportFragment instance;
    private MainViewModel<Order> orderViewModel;

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
        orderViewModel = new MainViewModel<>(new OrderRepositoryImpl(new FirebaseOrderService(FirebaseDatabase.getInstance().getReference())));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReportBinding.inflate(inflater, container, false);

        setupChart();

        return binding.getRoot();
    }

    private void setupChart() {

        orderViewModel.getAll(FirebasePath.PATH_COMPLETE_ORDER).observe(getViewLifecycleOwner(), this::addDataToEntry);
    }

    private void addDataToEntry(List<Order> orders) {
    }
}