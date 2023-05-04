package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.orderingsystem.databinding.FragmentShopBinding;
import com.example.orderingsystem.model.repository.MaterialRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseMaterialService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.view.adapter.MaterialAdapter;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.viewmodel.MaterialViewModel;
import com.google.firebase.database.FirebaseDatabase;

public class ShopFragment extends Fragment {

    private FragmentShopBinding binding;
    private static ShopFragment instance;
    private MaterialViewModel materialViewModel;

    private ShopFragment() {

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

        materialViewModel = new MaterialViewModel(new MaterialRepositoryImpl(new FirebaseMaterialService(FirebaseDatabase.getInstance().getReference())));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);

        displayShopItemOnRecycleView();

        return binding.getRoot();
    }

    private void displayShopItemOnRecycleView() {

        int columnNumber = 2;
        int columnSpace = 50; // px
        boolean includeEdge = true;

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columnNumber));

        binding.recyclerView.setHasFixedSize(true);

        MaterialAdapter materialAdapter = new MaterialAdapter();

        materialViewModel.getAll(FirebasePath.PATH_MATERIAL).observe(getViewLifecycleOwner(), materialAdapter::setShopItemList);

        binding.recyclerView.setAdapter(materialAdapter);

        materialAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void setOnItemClick(int position) {
                Intent intent = new Intent(getActivity(), MaterialDetailsActivity.class);
                Log.e("TAG", "setOnItemClick: " + materialAdapter.getMaterial(position).getItemId() );
                intent.putExtra("item_id", materialAdapter.getMaterial(position).getItemId());
                startActivity(intent);
            }
        });
    }
}