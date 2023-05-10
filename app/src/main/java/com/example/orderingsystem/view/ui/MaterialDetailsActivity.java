package com.example.orderingsystem.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.orderingsystem.databinding.ActivityItemDetailsBinding;
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
public class MaterialDetailsActivity extends AppCompatActivity {

    @Inject
    public AuthViewModel authViewModel;

    @Inject
    public MaterialViewModel materialViewModel;

    @Inject
    public OrderViewModel orderViewModel;

    @Inject
    public DatabaseReference reference;

    private ActivityItemDetailsBinding binding;
    private BottomSheetOrderQuantityBinding bottomSheetBinding;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        displaySelectedItemDetails();

    }

    private void displaySelectedItemDetails() {

        materialViewModel.getById(getItemIdFromIntent(), FirebasePath.PATH_MATERIAL).observe(this, shopItem -> {

            binding.itemName.setText(shopItem.getItemName());
            binding.itemPrice.setText(String.valueOf(shopItem.getPrice()));
            binding.itemQuantity.setText(String.valueOf(shopItem.getQuantity()));
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

        bottomSheetBinding.itemPrice.setText(String.valueOf(shopItem.getPrice()));

        String defaultQuantity = "1";
        bottomSheetBinding.quantity.setText(defaultQuantity);

        String itemPriceAtOnePiece = String.valueOf(shopItem.getPrice());
        bottomSheetBinding.itemPrice.setText(itemPriceAtOnePiece);
    }

    private void whenIncrementButtonClick(Material shopItem) {

        bottomSheetBinding.buttonIncremental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int newQuantity = incremental(Integer.parseInt(bottomSheetBinding.quantity.getText().toString()), shopItem.getQuantity());

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

        Order order = new Order();
        order.setOrderId(reference.push().getKey());
        order.setItemId(String.valueOf(material.getItemId()));
        order.setUserId(authViewModel.getCurrentUser().getUid());
        order.setItemName(material.getItemName());
        order.setQuantity(Integer.parseInt(quantity));
        order.setPrice(material.getPrice());
        order.setPurchaseDate(MyUtils.getFormattedDate(System.currentTimeMillis()));

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
        materialViewModel.removeById(itemId, getCurrentUserCartPath(""));
    }

    private String getCurrentUserOrderingPath(String key) {
        return MyUtils.addItemsWithSlashSeparator(FirebasePath.PATH_ORDER, authViewModel.getCurrentUser().getUid(), key);
    }

    private String getStorePath(String key) {
        return MyUtils.addItemsWithSlashSeparator(FirebasePath.PATH_INCOMING_ORDER, key);
    }

    private String getCurrentUserCartPath(String key) {
        return MyUtils.addItemsWithSlashSeparator(FirebasePath.PATH_CART, authViewModel.getCurrentUser().getUid(), key);
    }

    private void showSnackBar(View view) {
        Snackbar.make(MaterialDetailsActivity.this, view, "Add item successfully", Snackbar.LENGTH_SHORT).show();
    }

}