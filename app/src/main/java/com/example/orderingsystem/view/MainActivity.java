package com.example.orderingsystem.view;

import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.ActivityMainBinding;
import com.example.orderingsystem.view.ui.user.CartFragment;
import com.example.orderingsystem.view.ui.user.OrderFragment;
import com.example.orderingsystem.view.ui.user.ProfileFragment;
import com.example.orderingsystem.view.ui.user.ShopFragment;
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
//        setupNavController();
    }


    private void setupNavController() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_shop, R.id.navigation_cart, R.id.navigation_order, R.id.navigation_profile
        ).build();

        NavController navController = Navigation.findNavController(this, R.id.ui_container);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
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
}