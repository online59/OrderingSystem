package com.example.orderingsystem.view;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.ActivityMainBinding;
import com.example.orderingsystem.view.ui.CartFragment;
import com.example.orderingsystem.view.ui.OrderFragment;
import com.example.orderingsystem.view.ui.ProfileFragment;
import com.example.orderingsystem.view.ui.ShopFragment;
import com.google.android.material.navigation.NavigationBarView;
import dagger.hilt.android.AndroidEntryPoint;
import org.jetbrains.annotations.NotNull;
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
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