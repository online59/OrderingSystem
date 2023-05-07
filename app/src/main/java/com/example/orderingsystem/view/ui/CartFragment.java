package com.example.orderingsystem.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.orderingsystem.databinding.FragmentCartBinding;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.OrderRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseOrderService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.MyUtils;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.view.adapter.OrderAdapter;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.OrderViewModel;
import com.google.firebase.database.FirebaseDatabase;
import dagger.hilt.android.AndroidEntryPoint;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@AndroidEntryPoint
public class CartFragment extends Fragment {


    @Inject
    public OrderViewModel orderViewModel;
    @Inject
    public AuthViewModel authViewModel;

    private FragmentCartBinding binding;
    private static CartFragment instance;

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

        orderViewModel.getAll(getCurrentUserCartPath()).observe(getViewLifecycleOwner(), orderAdapter::setOrderList);

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

    private String getCurrentUserCartPath() {
        return MyUtils.addItemsWithSlashSeparator(FirebasePath.PATH_CART, getCurrentUserUid());
    }
    private String getCurrentUserUid() {
        return authViewModel.getCurrentUser().getUid();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        Log.e("CartFragment", "onAttach: called" );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("CartFragment", "onDetach: called" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("CartFragment", "onDestroy: called" );
    }
}