package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.orderingsystem.databinding.FragmentOrderBinding;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.OrderRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseOrderService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.utils.MyUtils;
import com.example.orderingsystem.view.adapter.OrderAdapter;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.OrderViewModel;
import com.google.firebase.database.FirebaseDatabase;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;
    private static OrderFragment instance;
    private OrderViewModel orderViewModel;
    private AuthViewModel authViewModel;

    private OrderFragment() {
    }

    public static OrderFragment getInstance() {
        if (instance == null) {
            instance = new OrderFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialSetup();
    }

    private void initialSetup() {
        orderViewModel = new OrderViewModel(new OrderRepositoryImpl(new FirebaseOrderService(FirebaseDatabase.getInstance().getReference())));
        authViewModel = new AuthViewModel(new AuthRepositoryImpl(new FirebaseAuthService()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOrderBinding.inflate(inflater, container, false);

        displayItemOnCartOnRecyclerView();

        return binding.getRoot();
    }


    private void displayItemOnCartOnRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        OrderAdapter orderAdapter = new OrderAdapter();

        orderViewModel.getAll(getCurrentUserOrderPath()).observe(getViewLifecycleOwner(), orderAdapter::setOrderList);

        binding.recyclerView.setAdapter(orderAdapter);

        orderAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void setOnItemClick(int position) {
                Intent intent = new Intent(getActivity(), MaterialDetailsActivity.class);
                intent.putExtra("item_id", orderAdapter.getOder(position).getItemId());
                startActivity(intent);
            }
        });
    }

    private String getCurrentUserUid() {
        return authViewModel.getCurrentUser().getUid();
    }

    private String getCurrentUserOrderPath() {
        return MyUtils.addItemsWithSlashSeparator(FirebasePath.PATH_ORDER, getCurrentUserUid());
    }
}