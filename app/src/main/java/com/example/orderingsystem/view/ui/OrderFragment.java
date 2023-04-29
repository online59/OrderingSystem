package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.FragmentOrderBinding;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.ShopItemRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseService;
import com.example.orderingsystem.view.adapter.ShopItemAdapter;
import com.example.orderingsystem.view.event.ItemClickListener;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.ItemViewModel;
import com.google.firebase.database.FirebaseDatabase;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;
    private static OrderFragment instance;
    private ItemViewModel itemViewModel;
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
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        initialSetup();
    }

    private void initialSetup() {
        itemViewModel = new ItemViewModel(new ShopItemRepositoryImpl(new FirebaseService(FirebaseDatabase.getInstance().getReference())));
        authViewModel = new AuthViewModel(new AuthRepositoryImpl(new FirebaseAuthService()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        displayItemOnCartOnRecyclerView();

        return inflater.inflate(R.layout.fragment_order, container, false);
    }


    private void displayItemOnCartOnRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        ShopItemAdapter shopItemAdapter = new ShopItemAdapter();

        itemViewModel.getAll("order/" + getCurrentUserUid()).observe(getViewLifecycleOwner(), shopItemAdapter::setShopItemList);

        shopItemAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void setOnItemClick(int position) {
                Intent intent = new Intent(getActivity(), ItemDetailsActivity.class);
                intent.putExtra("item_id", shopItemAdapter.getShopItemList(position).getItemId());
                startActivity(intent);
            }
        });
    }

    private String getCurrentUserUid() {
        return authViewModel.getCurrentUser().getUid();
    }
}