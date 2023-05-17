package com.example.orderingsystem.view.ui;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.orderingsystem.databinding.ActivityStoreMaterialDetailsBinding;
import com.example.orderingsystem.databinding.BottomSheetOrderQuantityBinding;
import com.example.orderingsystem.model.data.Material;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.MyUtils;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.MaterialViewModel;
import com.example.orderingsystem.viewmodel.OrderViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class StoreMaterialDetailsActivity extends AppCompatActivity {

    @Inject
    public AuthViewModel authViewModel;

    @Inject
    public MaterialViewModel materialViewModel;

    @Inject
    public OrderViewModel orderViewModel;

    @Inject
    public DatabaseReference reference;

    private ActivityStoreMaterialDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoreMaterialDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        displaySelectedItemDetails();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void displaySelectedItemDetails() {

        materialViewModel.getById(getItemIdFromIntent(), FirebasePath.PATH_MATERIAL).observe(this, shopItem -> {

            binding.itemName.setText(shopItem.getItemName());
            binding.itemPrice.setText(String.valueOf(shopItem.getPrice()));
            binding.itemQuantity.setText(String.valueOf(shopItem.getQuantity()));
            binding.itemDetails.setText(shopItem.getDescription());
            binding.itemYear.setText(String.valueOf(shopItem.getProduceYear()));

        });
    }

    private String getItemIdFromIntent() {
        return getIntent().getStringExtra("item_id");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}