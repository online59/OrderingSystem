package com.example.orderingsystem.view;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.ActivityMainBinding;
import com.example.orderingsystem.view.ui.user.CartFragment;
import com.example.orderingsystem.view.ui.user.OrderFragment;
import com.example.orderingsystem.view.ui.user.ProfileFragment;
import com.example.orderingsystem.view.ui.user.ShopFragment;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.google.android.material.navigation.NavigationBarView;
import dagger.hilt.android.AndroidEntryPoint;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Inject
    public AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupBottomNavBar();

        fetchDataFromNetwork();
    }

    private void fetchDataFromNetwork() {

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
                    setTitle("SHOP");
                    return true;
                case R.id.cart:
                    translateFragment(R.id.ui_container, CartFragment.getInstance());
                    setTitle("CART");
                    return true;
                case R.id.order:
                    translateFragment(R.id.ui_container, OrderFragment.getInstance());
                    setTitle("MY ORDER");
                    return true;
                case R.id.profile:
                    translateFragment(R.id.ui_container, ProfileFragment.getInstance());
                    setTitle("PROFILE");
                    return true;
            }
            return false;
        }
    };

    private void translateFragment(int container, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container, fragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        authViewModel = null;
    }
}