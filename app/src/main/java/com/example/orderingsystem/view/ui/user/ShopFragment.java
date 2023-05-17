package com.example.orderingsystem.view.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.orderingsystem.databinding.FragmentShopBinding;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.view.adapter.MaterialAdapter;
import com.example.orderingsystem.view.ui.MaterialDetailsActivity;
import com.example.orderingsystem.viewmodel.MaterialViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@AndroidEntryPoint
public class ShopFragment extends Fragment {

    private static ShopFragment instance;
    @Inject
    public MaterialViewModel materialViewModel;
    private FragmentShopBinding binding;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);

        displayAllItems();

        handlingFetchingDataRequestEvent();

        return binding.getRoot();
    }

    private void handlingFetchingDataRequestEvent() {
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "Fetching data from network, please wait...", Toast.LENGTH_SHORT).show();
                binding.swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void displayAllItems() {

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
                Log.e("TAG", "setOnItemClick: " + materialAdapter.getMaterial(position).getItemId());
                intent.putExtra("item_id", materialAdapter.getMaterial(position).getItemId());
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        Log.e("ShopFragment", "onAttach: called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("ShopFragment", "onDetach: called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ShopFragment", "onDestroy: called");
        instance = null;
    }
}