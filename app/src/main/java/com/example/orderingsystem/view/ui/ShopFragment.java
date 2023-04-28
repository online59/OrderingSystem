package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingsystem.R;
import com.example.orderingsystem.model.data.Item;
import com.example.orderingsystem.model.repository.RepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseService;
import com.example.orderingsystem.view.adapter.ItemAdapter;
import com.example.orderingsystem.view.event.ItemClickListener;
import com.example.orderingsystem.viewmodel.MainViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ShopFragment extends Fragment {

    private static ShopFragment instance;
    private MainViewModel viewModel;

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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        viewModel = new MainViewModel(new RepositoryImpl(new FirebaseService(reference)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        displayItemInRecycleView(view);
        return view;
    }

    private void displayItemInRecycleView(View view) {
        RecyclerView itemRecyclerView = view.findViewById(R.id.recycler_view);
        itemRecyclerView.setHasFixedSize(true);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        ItemAdapter adapter = new ItemAdapter();

        viewModel.getAll("items").observe(getViewLifecycleOwner(), adapter::setItemList);

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void setOnItemClick(int position) {
                Intent intent = new Intent(getActivity(), ItemDetailsActivity.class);
                intent.putExtra("item_id", adapter.getItemList(position).getItemId());
                startActivity(intent);
            }
        });
    }
}