package com.example.orderingsystem.view.ui;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.ActivityItemDetailsBinding;
import com.example.orderingsystem.model.repository.ShopItemRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseService;
import com.example.orderingsystem.viewmodel.ItemViewModel;
import com.google.firebase.database.FirebaseDatabase;

public class ItemDetailsActivity extends AppCompatActivity {

    private ActivityItemDetailsBinding binding;
    private ItemViewModel itemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        binding = ActivityItemDetailsBinding.inflate(getLayoutInflater());

        setup();
    }

    private void setup() {
        itemViewModel = new ItemViewModel(new ShopItemRepositoryImpl(new FirebaseService(FirebaseDatabase.getInstance().getReference())));
    }

    private String getItemIdFromIntent() {
        return new Intent().getStringExtra("item_id");
    }
}