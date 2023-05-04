package com.example.orderingsystem.view.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.room.Room;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.FragmentReportBinding;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.database.OrderDatabase;
import com.example.orderingsystem.model.repository.OrderRepositoryImpl;
import com.example.orderingsystem.model.room.RoomRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseOrderService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.MyUtils;
import com.example.orderingsystem.viewmodel.OrderViewModel;
import com.example.orderingsystem.viewmodel.RoomViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReportFragment extends Fragment {

    private FragmentReportBinding binding;
    private static ReportFragment instance;
    private OrderViewModel orderViewModel;
    private RoomViewModel roomViewModel;

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

        orderViewModel = new OrderViewModel(new OrderRepositoryImpl(new FirebaseOrderService(FirebaseDatabase.getInstance().getReference())));

        OrderDatabase database = Room.databaseBuilder(getActivity(), OrderDatabase.class, "orders").build();
        roomViewModel = new RoomViewModel(new RoomRepositoryImpl(database));
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

    private void passDataToLocalDatabase(List<Order> orders) {

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            roomViewModel.insertAll(orders);
            new Handler(Looper.getMainLooper()).post(this::addDataToEntry);
        });

    }

    private void addDataToEntry() {

        List<Entry> lineChartData = new ArrayList<>();

        String[] dates = MyUtils.getDatesInRange(1, 5, 2023, 7);

        for (String day: dates) {

            roomViewModel.getByDate(day).observe(getViewLifecycleOwner(), order -> {
                lineChartData.add(new Entry(order.getPrice(), MyUtils.getTimeStamp(order.getPurchaseDate())));
            });
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

    }
}