package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.orderingsystem.databinding.FragmentStoreOrderBinding;
import com.example.orderingsystem.model.data.Material;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.MaterialRepositoryImpl;
import com.example.orderingsystem.model.repository.OrderRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseMaterialService;
import com.example.orderingsystem.model.service.FirebaseOrderService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.view.adapter.OrderAdapter;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.MainViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoreOrderFragment extends Fragment {

    private FragmentStoreOrderBinding binding;
    private static StoreOrderFragment instance;
    private MainViewModel<Order> orderViewModel;
    private MainViewModel<Material> itemViewModel;

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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        orderViewModel = new MainViewModel<>(new OrderRepositoryImpl(new FirebaseOrderService(reference)));
        itemViewModel = new MainViewModel<>(new MaterialRepositoryImpl(new FirebaseMaterialService(reference)));
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
                orderViewModel.write(order, FirebasePath.PATH_COMPLETE_ORDER + "/" + order.getOrderId());

                // Remove order from store relative path
                orderViewModel.removeById(order.getOrderId(), FirebasePath.PATH_INCOMING_ORDER);

                // Remove order from user relative path
                itemViewModel.removeById(order.getOrderId(), FirebasePath.PATH_ORDER + "/" + order.getUserId());

                Toast.makeText(getActivity(), "Order accepted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}