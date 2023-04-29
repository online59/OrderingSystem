package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.FragmentCartBinding;
import com.example.orderingsystem.model.repository.ShopItemRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseService;
import com.example.orderingsystem.view.adapter.ShopItemAdapter;
import com.example.orderingsystem.view.event.ItemClickListener;
import com.example.orderingsystem.viewmodel.ItemViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private static CartFragment instance;
    private ItemViewModel viewModel;

    private CartFragment() {
        // Required empty public constructor
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
        // View biding
        binding = FragmentCartBinding.inflate(getLayoutInflater());
        initialSetup();
    }

    private void initialSetup() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        viewModel = new ItemViewModel(new ShopItemRepositoryImpl(new FirebaseService(reference)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        displayItemOnCartOnRecyclerView(view);
        return view;
    }

    private void displayItemOnCartOnRecyclerView(View view) {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        ShopItemAdapter adapter = new ShopItemAdapter();

        // TODO
        // Add user id
        viewModel.getAll("orders/userId").observe(getViewLifecycleOwner(), adapter::setShopItemList);

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void setOnItemClick(int position) {
                Intent intent = new Intent(getActivity(), ItemDetailsActivity.class);
                intent.putExtra("item_id", adapter.getShopItemList(position).getItemId());
                startActivity(intent);
            }
        });
    }
}