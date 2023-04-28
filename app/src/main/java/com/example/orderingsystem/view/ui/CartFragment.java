package com.example.orderingsystem.view.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.orderingsystem.R;
import com.example.orderingsystem.model.data.Item;
import com.example.orderingsystem.model.repository.RepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseService;
import com.example.orderingsystem.viewmodel.MyViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartFragment extends Fragment {

    private static CartFragment instance;
    private MyViewModel<Item> viewModel;

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
        initialSetup();
    }

    private void initialSetup() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        viewModel = new MyViewModel<>(new RepositoryImpl(new FirebaseService(reference)));
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

    }
}