package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.orderingsystem.databinding.ActivityItemDetailsBinding;
import com.example.orderingsystem.model.data.GeneralOrder;
import com.example.orderingsystem.model.data.Ordering;
import com.example.orderingsystem.model.data.ShopItem;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.OrderRepositoryImpl;
import com.example.orderingsystem.model.repository.ShopItemRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseItemService;
import com.example.orderingsystem.model.service.FirebaseOrderService;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.MainViewModel;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class ItemDetailsActivity extends AppCompatActivity {

    private ActivityItemDetailsBinding binding;
    private AuthViewModel authViewModel;
    private MainViewModel<ShopItem>  itemViewModel;
    private MainViewModel<Ordering> orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setup();

        displaySelectedItemDetails();

    }

    private void setup() {
        authViewModel = new AuthViewModel(new AuthRepositoryImpl(new FirebaseAuthService()));
        itemViewModel = new MainViewModel<>(new ShopItemRepositoryImpl(new FirebaseItemService(FirebaseDatabase.getInstance().getReference())));
        orderViewModel = new MainViewModel<>(new OrderRepositoryImpl(new FirebaseOrderService(FirebaseDatabase.getInstance().getReference())));
    }

    private void displaySelectedItemDetails() {
        itemViewModel.getById(getItemIdFromIntent(), "material").observe(this, shopItem -> {
            binding.itemName.setText(shopItem.getItemName());
            binding.itemPrice.setText(String.valueOf(shopItem.getPrice()));
            binding.itemQuantity.setText(String.valueOf(shopItem.getRemaining()));
            binding.itemDetails.setText(shopItem.getDescription());
            binding.itemYear.setText(shopItem.getProduceYear());

            setWhenOrderButtonClick(shopItem);
        });
    }

    private String getItemIdFromIntent() {
        return new Intent().getStringExtra("item_id");
    }

    private void setWhenOrderButtonClick(ShopItem shopItem) {
        binding.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ordering order = createOrderData(shopItem);

                orderViewModel.write(order, getCurrentUserOrderingPath(order));
            }
        });
    }


    private Ordering createOrderData(ShopItem shopItem) {

        Ordering order = new GeneralOrder();
        order.setItemId(String.valueOf(shopItem.getItemId()));
        order.setOrderId(String.valueOf(getRandomInt()));
        order.setNumberItemOrdered(1);
        order.setPrice(shopItem.getPrice());

        return order;
    }


    private String getCurrentUserOrderingPath(Ordering ordering) {
        return "order/" + authViewModel.getCurrentUser().getUid() + "/" + ordering.getOrderId();
    }

    private int getRandomInt() {
        return new Random().nextInt();
    }

    private boolean isFieldsNull(String...str) {
        for (String value: str) {
            if (value == null || value.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}