package com.example.orderingsystem.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.orderingsystem.databinding.FragmentOrderBinding;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.utils.MyUtils;
import com.example.orderingsystem.view.adapter.OrderAdapter;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.OrderViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@AndroidEntryPoint
public class OrderFragment extends Fragment {

    private static OrderFragment instance;
    @Inject
    public OrderViewModel orderViewModel;
    @Inject
    public AuthViewModel authViewModel;
    private FragmentOrderBinding binding;

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

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        Log.e("OrderFragment", "onAttach: called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("OrderFragment", "onDetach: called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("OrderFragment", "onDestroy: called");
    }
}