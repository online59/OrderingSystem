package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.orderingsystem.databinding.FragmentCartBinding;
import com.example.orderingsystem.model.data.Material;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.MaterialRepositoryImpl;
import com.example.orderingsystem.model.repository.OrderRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseMaterialService;
import com.example.orderingsystem.model.service.FirebaseOrderService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.view.adapter.MaterialAdapter;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.view.adapter.OrderAdapter;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.MainViewModel;
import com.google.firebase.database.FirebaseDatabase;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private static CartFragment instance;
    private MainViewModel<Order> itemViewModel;
    private AuthViewModel authViewModel;

    private CartFragment() {
    }

    public static CartFragment getInstance() {
        if (instance == null) {
            instance = new CartFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialSetup();
    }

    private void initialSetup() {
        itemViewModel = new MainViewModel<>(new OrderRepositoryImpl(new FirebaseOrderService(FirebaseDatabase.getInstance().getReference())));
        authViewModel = new AuthViewModel(new AuthRepositoryImpl(new FirebaseAuthService()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);

        displayItemOnCartOnRecyclerView();

        return binding.getRoot();
    }

    private void displayItemOnCartOnRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        OrderAdapter orderAdapter = new OrderAdapter();

        itemViewModel.getAll(FirebasePath.PATH_CART + "/" + getCurrentUserUid()).observe(getViewLifecycleOwner(), orderAdapter::setOrderList);

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
}