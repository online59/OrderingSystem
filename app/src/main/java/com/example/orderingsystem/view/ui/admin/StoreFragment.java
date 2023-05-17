package com.example.orderingsystem.view.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.FragmentStoreBinding;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.view.adapter.MaterialAdapter;
import com.example.orderingsystem.view.ui.MaterialDetailsActivity;
import com.example.orderingsystem.view.ui.StoreMaterialDetailsActivity;
import com.example.orderingsystem.viewmodel.MaterialViewModel;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class StoreFragment extends Fragment {

    private static StoreFragment instance;

    @Inject
    public MaterialViewModel materialViewModel;

    private FragmentStoreBinding binding;

    private StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment getInstance() {
        if (instance == null) {
            instance = new StoreFragment();
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
        binding = FragmentStoreBinding.inflate(inflater, container, false);

        displayItemOnStore();

        return binding.getRoot();
    }

    private void displayItemOnStore() {

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
                Intent intent = new Intent(getActivity(), StoreMaterialDetailsActivity.class);
                Log.e("TAG", "setOnItemClick: " + materialAdapter.getMaterial(position).getItemId());
                intent.putExtra("item_id", materialAdapter.getMaterial(position).getItemId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }
}