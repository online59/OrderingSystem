package com.example.orderingsystem.view.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.orderingsystem.databinding.FragmentStoreProfileBinding;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.view.ui.SignInActivity;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.UserViewModel;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class StoreProfileFragment extends Fragment {

    private static StoreProfileFragment instance;
    @Inject
    public AuthViewModel authViewModel;
    @Inject
    public UserViewModel userViewModel;
    private FragmentStoreProfileBinding binding;

    private StoreProfileFragment() {
    }

    public static StoreProfileFragment getInstance() {
        if (instance == null) {
            instance = new StoreProfileFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStoreProfileBinding.inflate(inflater, container, false);

        setValueForView();
        whenSignOutButtonClick();
        whenAddNewItemButtonClick();

        return binding.getRoot();
    }


    private void setValueForView() {
        userViewModel.getById(authViewModel.getCurrentUser().getUid(), FirebasePath.PATH_USER).observe(getViewLifecycleOwner(), user -> {
            binding.userFullName.setText(user.getFullName());
            binding.userEmail.setText(user.getEmail());
            binding.userEmailDetail.setText(user.getEmail());
        });
    }


    private void whenSignOutButtonClick() {
        binding.buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUserOut();
                startActivity(new Intent(getActivity(), SignInActivity.class));
            }
        });
    }

    private void whenAddNewItemButtonClick() {
        binding.buttonAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddEditActivity.class));
            }
        });
    }

    private void signUserOut() {
        authViewModel.signOut();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }
}