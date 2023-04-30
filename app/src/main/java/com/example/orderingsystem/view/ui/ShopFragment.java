package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.orderingsystem.databinding.FragmentShopBinding;
import com.example.orderingsystem.model.data.Material;
import com.example.orderingsystem.model.repository.MaterialRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseItemService;
import com.example.orderingsystem.view.adapter.MaterialAdapter;
import com.example.orderingsystem.view.event.ItemClickListener;
import com.example.orderingsystem.viewmodel.MainViewModel;
import com.google.firebase.database.FirebaseDatabase;


public class ShopFragment extends Fragment {

    private FragmentShopBinding binding;
    private static ShopFragment instance;
    private MainViewModel<Material> itemViewModel;

    private ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment getInstance() {
        if (instance == null) {
            instance = new ShopFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);

        displayShopItemOnRecycleView();

        return binding.getRoot();
    }

    private void displayShopItemOnRecycleView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        MaterialAdapter materialAdapter = new MaterialAdapter();

        itemViewModel.getAll("material").observe(getViewLifecycleOwner(), materialAdapter::setShopItemList);

        binding.recyclerView.setAdapter(materialAdapter);

        materialAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void setOnItemClick(int position) {
                Intent intent = new Intent(getActivity(), ItemDetailsActivity.class);
                Log.e("TAG", "setOnItemClick: " + materialAdapter.getShopItemList(position).getItemId() );
                intent.putExtra("item_id", materialAdapter.getShopItemList(position).getItemId());
                startActivity(intent);
            }
        });
    }
}