package com.example.orderingsystem.view.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.orderingsystem.databinding.FragmentProfileBinding;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.view.ui.SignInActivity;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.UserViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    private static ProfileFragment instance;
    @Inject
    public AuthViewModel authViewModel;
    @Inject
    public UserViewModel userViewModel;
    private FragmentProfileBinding binding;

    private ProfileFragment() {

    }

    public static ProfileFragment getInstance() {
        if (instance == null) {
            instance = new ProfileFragment();
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

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        setValueForView();
        whenSignOutButtonClick();

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
                startActivity(new Intent(requireActivity(), SignInActivity.class));
            }
        });
    }

    private void signUserOut() {
        authViewModel.signOut();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        Log.e("ProfileFragment", "onAttach: called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("ProfileFragment", "onDetach: called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ProfileFragment", "onDestroy: called");
        instance = null;
    }
}