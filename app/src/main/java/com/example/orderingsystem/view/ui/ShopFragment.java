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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.orderingsystem.databinding.FragmentShopBinding;
import com.example.orderingsystem.model.repository.MaterialRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseMaterialService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.view.adapter.MaterialAdapter;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.viewmodel.MaterialViewModel;
import com.google.firebase.database.FirebaseDatabase;
import dagger.hilt.android.AndroidEntryPoint;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@AndroidEntryPoint
public class ShopFragment extends Fragment {

    public MaterialViewModel materialViewModel;

    private FragmentShopBinding binding;
    private static ShopFragment instance;

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

        materialViewModel = new ViewModelProvider(this).get(MaterialViewModel.class);
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

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        Log.e("ShopFragment", "onAttach: called" );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("ShopFragment", "onDetach: called" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ShopFragment", "onDestroy: called" );
    }
}