package com.example.orderingsystem.view.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.orderingsystem.databinding.FragmentStoreOrderBinding;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.repository.MaterialRepositoryImpl;
import com.example.orderingsystem.model.repository.OrderRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseMaterialService;
import com.example.orderingsystem.model.service.FirebaseOrderService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.utils.MyUtils;
import com.example.orderingsystem.view.adapter.OrderAdapter;
import com.example.orderingsystem.viewmodel.MaterialViewModel;
import com.example.orderingsystem.viewmodel.OrderViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class StoreOrderFragment extends Fragment {

    @Inject
    public OrderViewModel orderViewModel;
    @Inject
    public MaterialViewModel materialViewModel;

    private FragmentStoreOrderBinding binding;
    private static StoreOrderFragment instance;

    private StoreOrderFragment() {
        // Required empty public constructor
    }

    public static StoreOrderFragment getInstance() {

        if (instance == null) {
            instance = new StoreOrderFragment();
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

        binding = FragmentStoreOrderBinding.inflate(inflater, container, false);

        displayItemOnCartOnRecyclerView();

        return binding.getRoot();
    }


    private void displayItemOnCartOnRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        OrderAdapter orderAdapter = new OrderAdapter();

        orderViewModel.getAll(FirebasePath.PATH_INCOMING_ORDER).observe(getViewLifecycleOwner(), orderAdapter::setOrderList);

        binding.recyclerView.setAdapter(orderAdapter);

        // Whoever can get to this page is a system admin
        orderAdapter.setAdmin(true);

        orderAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void setOnItemClick(int position) {

                Order order = orderAdapter.getOder(position);

                // Record complete order
                orderViewModel.write(order, getOrderCompletePath(order));

                // Remove order from store relative path
                orderViewModel.removeById(order.getOrderId(), FirebasePath.PATH_INCOMING_ORDER);

                // Remove order from user relative path
                materialViewModel.removeById(order.getOrderId(), getIncomingOrderPath(order));

                Toast.makeText(getActivity(), "Order accepted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getOrderCompletePath(Order order) {
        return MyUtils.addItemsWithSlashSeparator(FirebasePath.PATH_COMPLETE_ORDER, order.getOrderId());
    }

    private String getIncomingOrderPath(Order order) {
        return MyUtils.addItemsWithSlashSeparator(FirebasePath.PATH_ORDER, order.getUserId());
    }
}