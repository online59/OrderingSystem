package com.example.orderingsystem.view.ui;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.orderingsystem.databinding.ActivityItemDetailsBinding;
import com.example.orderingsystem.model.data.GeneralOrder;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.data.Material;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.OrderRepositoryImpl;
import com.example.orderingsystem.model.repository.MaterialRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseMaterialService;
import com.example.orderingsystem.model.service.FirebaseOrderService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MaterialDetailsActivity extends AppCompatActivity {

    private ActivityItemDetailsBinding binding;
    private AuthViewModel authViewModel;
    private MainViewModel<Material>  itemViewModel;
    private MainViewModel<Order> orderViewModel;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setup();

        displaySelectedItemDetails();

    }

    private void setup() {
        reference = FirebaseDatabase.getInstance().getReference();
        authViewModel = new AuthViewModel(new AuthRepositoryImpl(new FirebaseAuthService()));
        itemViewModel = new MainViewModel<>(new MaterialRepositoryImpl(new FirebaseMaterialService(reference)));
        orderViewModel = new MainViewModel<>(new OrderRepositoryImpl(new FirebaseOrderService(reference)));
    }

    private void displaySelectedItemDetails() {
        itemViewModel.getById(getMaterialIdFromIntent(), FirebasePath.PATH_MATERIAL).observe(this, shopItem -> {

            binding.itemName.setText(shopItem.getItemName());
            binding.itemPrice.setText(String.valueOf(shopItem.getPrice()));
            binding.itemQuantity.setText(String.valueOf(shopItem.getRemaining()));
            binding.itemDetails.setText(shopItem.getDescription());
            binding.itemYear.setText(String.valueOf(shopItem.getProduceYear()));

            setWhenOrderButtonClick(shopItem);
            setWhenAddToCartButtonClick(shopItem);
        });
    }

    private String getMaterialIdFromIntent() {
        return getIntent().getStringExtra("item_id");
    }

    private void setWhenOrderButtonClick(Material shopItem) {
        binding.buttonBuyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order order = createOrderData(shopItem);

                orderViewModel.write(order, getCurrentUserOrderingPath(order));
                
                showSnackBar(view);
            }
        });
    }

    private void setWhenAddToCartButtonClick(Material shopItem) {
        binding.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order order = createOrderData(shopItem);

                orderViewModel.write(order, getCurrentUserCartPath(order));

                showSnackBar(view);
            }
        });
    }

    private Order createOrderData(Material material) {

        Order order = new GeneralOrder();
        order.setOrderId(reference.push().getKey());
        order.setItemId(String.valueOf(material.getItemId()));
        order.setItemName(material.getItemName());
        order.setQuantity(1);
        order.setPrice(material.getPrice());

        return order;
    }

    private String getCurrentUserOrderingPath(Order order) {
        return FirebasePath.PATH_ORDER+ "/" + authViewModel.getCurrentUser().getUid() + "/" + order.getOrderId();
    }

    private String getCurrentUserCartPath(Order order) {
        return FirebasePath.PATH_CART+ "/" + authViewModel.getCurrentUser().getUid() + "/" + order.getOrderId();
    }

    private void showSnackBar(View view) {
        Snackbar.make(MaterialDetailsActivity.this, view, "Add item successfully", Snackbar.LENGTH_SHORT).show();
    }

}