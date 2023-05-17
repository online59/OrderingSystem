package com.example.orderingsystem.view;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.ActivityAdminMainBinding;
import com.example.orderingsystem.view.ui.admin.StoreFragment;
import com.example.orderingsystem.view.ui.admin.StoreSalesReportFragment;
import com.example.orderingsystem.view.ui.admin.StoreProfileFragment;
import com.example.orderingsystem.view.ui.admin.StoreOrderFragment;
import com.example.orderingsystem.view.ui.user.ShopFragment;
import com.google.android.material.navigation.NavigationBarView;
import dagger.hilt.android.AndroidEntryPoint;
import org.jetbrains.annotations.NotNull;

@AndroidEntryPoint
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
                    translateFragment(R.id.ui_container, StoreFragment.getInstance());
                    setTitle("STORE");
                    return true;
                case R.id.order:
                    translateFragment(R.id.ui_container, StoreOrderFragment.getInstance());
                    setTitle("INCOMING ORDER");
                    return true;
                case R.id.report:
                    translateFragment(R.id.ui_container, StoreSalesReportFragment.getInstance());
                    setTitle("SALES REPORT");
                    return true;
                case R.id.store:
                    translateFragment(R.id.ui_container, StoreProfileFragment.getInstance());
                    setTitle("STORE PROFILE");
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