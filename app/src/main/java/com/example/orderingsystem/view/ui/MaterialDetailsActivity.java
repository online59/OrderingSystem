package com.example.orderingsystem.view.ui;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.orderingsystem.databinding.ActivityItemDetailsBinding;
import com.example.orderingsystem.databinding.BottomSheetOrderQuantityBinding;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MaterialDetailsActivity extends AppCompatActivity {

    private ActivityItemDetailsBinding binding;
    private BottomSheetOrderQuantityBinding bottomSheetBinding;
    private AuthViewModel authViewModel;
    private MainViewModel<Material>  itemViewModel;
    private MainViewModel<Order> orderViewModel;
    private DatabaseReference reference;
    private BottomSheetDialog bottomSheetDialog;

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

        itemViewModel.getById(getItemIdFromIntent(), FirebasePath.PATH_MATERIAL).observe(this, shopItem -> {

            binding.itemName.setText(shopItem.getItemName());
            binding.itemPrice.setText(String.valueOf(shopItem.getPrice()));
            binding.itemQuantity.setText(String.valueOf(shopItem.getRemaining()));
            binding.itemDetails.setText(shopItem.getDescription());
            binding.itemYear.setText(String.valueOf(shopItem.getProduceYear()));

            showDialogWhenOrderButtonClick(shopItem);
            addItemToCartWhenAddToCartButtonClick(shopItem);
        });
    }

    private String getItemIdFromIntent() {
        return getIntent().getStringExtra("item_id");
    }

    private void showDialogWhenOrderButtonClick(Material shopItem) {
        binding.buttonBuyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogToSelectQuantity(shopItem);

            }
        });
    }

    private void addItemToCartWhenAddToCartButtonClick(Material shopItem) {
        binding.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String defaultQuantity = "1";
                Order order = createOrderData(shopItem, defaultQuantity);

                orderViewModel.write(order, getCurrentUserCartPath(order.getItemId()));

                showSnackBar(view);
            }
        });
    }

    private void showDialogToSelectQuantity(Material shopItem) {

        setupDialog();
        setupUi(shopItem);

        // Event handling
        whenIncrementButtonClick(shopItem);
        whenDecrementButtonClick(shopItem);
        whenBuyButtonClick(shopItem);

        bottomSheetDialog.show();
    }

    private void setupDialog() {
        bottomSheetBinding = BottomSheetOrderQuantityBinding.inflate(getLayoutInflater());

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(bottomSheetBinding.getRoot());
    }


    private void setupUi(Material shopItem) {

        bottomSheetBinding.itemName.setText(shopItem.getItemName());

        String defaultQuantity = "1";
        bottomSheetBinding.quantity.setText(defaultQuantity);

        String itemPriceAtOnePiece = String.valueOf(shopItem.getPrice());
        bottomSheetBinding.itemPrice.setText(itemPriceAtOnePiece);
    }
    private void whenIncrementButtonClick(Material shopItem) {

        bottomSheetBinding.buttonIncremental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int newQuantity = incremental(Integer.parseInt(bottomSheetBinding.quantity.getText().toString()), shopItem.getRemaining());

                // Update ui
                updateQuantityAndPrice(newQuantity, shopItem.getPrice());
            }
        });
    }

    private void whenDecrementButtonClick(Material shopItem) {

        bottomSheetBinding.buttonDecremental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newQuantity = decremental(Integer.parseInt(bottomSheetBinding.quantity.getText().toString()));

                // Update ui
                updateQuantityAndPrice(newQuantity, shopItem.getPrice());
            }
        });
    }

    private void whenBuyButtonClick(Material shopItem) {

        bottomSheetBinding.buttonBuyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order order = createOrderData(shopItem, bottomSheetBinding.quantity.getText().toString());

                // Add order to user relative path
                orderViewModel.write(order, getCurrentUserOrderingPath(order.getOrderId()));

                // Add order to store relative path
                orderViewModel.write(order, getStorePath(order.getOrderId()));

                // If the ordered item is also in cart
                removeItemFromCart(shopItem.getItemId());

                // Close dialog
                bottomSheetDialog.dismiss();

                showSnackBar(view);

                Toast.makeText(MaterialDetailsActivity.this, "Successfully order the item.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateQuantityAndPrice(int newQuantity, float price) {

        bottomSheetBinding.quantity.setText(String.valueOf(newQuantity));
        bottomSheetBinding.itemPrice.setText(String.valueOf(newQuantity * price));
    }

    private Order createOrderData(Material material, String quantity) {

        Order order = new GeneralOrder();
        order.setOrderId(reference.push().getKey());
        order.setItemId(String.valueOf(material.getItemId()));
        order.setUserId(authViewModel.getCurrentUser().getUid());
        order.setItemName(material.getItemName());
        order.setQuantity(Integer.parseInt(quantity));
        order.setPrice(material.getPrice());

        return order;
    }

    private int incremental(int quantity, int remaining) {

        if (quantity < remaining) {

            return quantity + 1;

        } else return quantity;
    }

    private int decremental(int quantity) {

        if (quantity > 1) {

            return quantity - 1;

        } else return quantity;
    }

    private void removeItemFromCart(String itemId) {
        itemViewModel.removeById(itemId, getCurrentUserCartPath(""));
    }

    private String getCurrentUserOrderingPath(String key) {
        return FirebasePath.PATH_ORDER+ "/" + authViewModel.getCurrentUser().getUid() + "/" + key;
    }

    private String getStorePath(String key) {
        return FirebasePath.PATH_INCOMING_ORDER+ "/" + key;
    }

    private String getCurrentUserCartPath(String key) {
        return FirebasePath.PATH_CART+ "/" + authViewModel.getCurrentUser().getUid() + "/" + key;
    }

    private void showSnackBar(View view) {
        Snackbar.make(MaterialDetailsActivity.this, view, "Add item successfully", Snackbar.LENGTH_SHORT).show();
    }

}