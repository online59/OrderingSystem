package com.example.orderingsystem.view.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.orderingsystem.databinding.FragmentEditItemBinding;

public class EditItemFragment extends Fragment {

    private FragmentEditItemBinding binding;

    private EditItemFragment() {
    }

    public static EditItemFragment newInstance() {
        return new EditItemFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEditItemBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}