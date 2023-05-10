package com.example.orderingsystem.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.ActivityAddEditBinding;

public class AddEditActivity extends AppCompatActivity {

    private ActivityAddEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        translateFragment(R.id.ui_container, AddItemFragment.newInstance());
    }

    private void translateFragment(int container, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container, fragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }
}