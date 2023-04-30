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
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.MaterialRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseItemService;
import com.example.orderingsystem.view.adapter.MaterialAdapter;
import com.example.orderingsystem.view.event.ItemClickListener;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.MainViewModel;
import com.google.firebase.database.FirebaseDatabase;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private static CartFragment instance;
    private MainViewModel<Material> itemViewModel;
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
        itemViewModel = new MainViewModel<>(new MaterialRepositoryImpl(new FirebaseItemService(FirebaseDatabase.getInstance().getReference())));
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

        MaterialAdapter materialAdapter = new MaterialAdapter();

        itemViewModel.getAll("cart/" + getCurrentUserUid()).observe(getViewLifecycleOwner(), materialAdapter::setShopItemList);

        materialAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void setOnItemClick(int position) {
                Intent intent = new Intent(getActivity(), ItemDetailsActivity.class);
                intent.putExtra("item_id", materialAdapter.getShopItemList(position).getItemId());
                startActivity(intent);
            }
        });
    }

    private String getCurrentUserUid() {
        return authViewModel.getCurrentUser().getUid();
    }
}