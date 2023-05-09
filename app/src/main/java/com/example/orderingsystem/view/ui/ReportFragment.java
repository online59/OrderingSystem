package com.example.orderingsystem.view.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.FragmentReportBinding;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.repository.OrderRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseOrderService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.MyUtils;
import com.example.orderingsystem.viewmodel.OrderViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.FirebaseDatabase;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@AndroidEntryPoint
public class ReportFragment extends Fragment {

    @Inject
    public OrderViewModel orderViewModel;

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

        loadDataFromFirebase();

        return binding.getRoot();
    }

    private void loadDataFromFirebase() {

        orderViewModel.getAll(FirebasePath.PATH_COMPLETE_ORDER).observe(getViewLifecycleOwner(), orders -> {

            List<Entry> lineChartData = new ArrayList<>();

            // Add dummy data to chart
            for (int orderNum = 0; orderNum < orders.size(); orderNum++) {
                lineChartData.add(new Entry(orderNum, MyUtils.getRandomFloat()));
            }

            LineDataSet lineDataSet = new LineDataSet(lineChartData, "Date");
            lineDataSet.setCircleRadius(10f);
            lineDataSet.setDrawFilled(true);
            lineDataSet.setValueTextSize(20F);
            lineDataSet.setFillColor(getResources().getColor(R.color.purple_500));
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

            LineData lineData = new LineData(lineDataSet);
            binding.saleReportChart.setData(lineData);
            binding.saleReportChart.setBackgroundColor(getResources().getColor(R.color.white));
            binding.saleReportChart.animateXY(2000, 2000, Easing.EaseInCubic);

        });
    }

    private void addDataToEntry() {

        List<Entry> lineChartData = new ArrayList<>();

        LineDataSet lineDataSet = new LineDataSet(lineChartData, "Date");
        lineDataSet.setCircleRadius(10f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setValueTextSize(20F);
        lineDataSet.setFillColor(getResources().getColor(R.color.purple_500));
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData(lineDataSet);
        binding.saleReportChart.setData(lineData);
        binding.saleReportChart.setBackgroundColor(getResources().getColor(R.color.white));
        binding.saleReportChart.animateXY(2000, 2000, Easing.EaseInCubic);

    }
}