package com.example.orderingsystem.view;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.ActivityAdminMainBinding;
import com.example.orderingsystem.view.ui.*;
import com.google.android.material.navigation.NavigationBarView;
import org.jetbrains.annotations.NotNull;

public class AdminMainActivity extends AppCompatActivity {

    private ActivityAdminMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupBottomNavBar();
    }

    private void setupBottomNavBar() {
        binding.bottomNavigationView.setOnItemSelectedListener(onNavBarItemSelect);
        binding.bottomNavigationView.setSelectedItemId(R.id.shop);
    }

    private final NavigationBarView.OnItemSelectedListener onNavBarItemSelect = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.shop:
                    translateFragment(R.id.ui_container, ShopFragment.getInstance());
                    return true;
                case R.id.cart:
                    translateFragment(R.id.ui_container, CartFragment.getInstance());
                    return true;
                case R.id.order:
                    translateFragment(R.id.ui_container, OrderFragment.getInstance());
                    return true;
                case R.id.report:
                    translateFragment(R.id.ui_container, ReportFragment.getInstance());
                    return true;
                case R.id.profile:
                    translateFragment(R.id.ui_container, ProfileFragment.getInstance());
                    return true;
            }
            return false;
        }
    };

    private void translateFragment(int container, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(container, fragment).commit();
    }
}