package com.example.orderingsystem.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.ActivityAddEditBinding;

public class AddEditActivity extends AppCompatActivity {

    private ActivityAddEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}