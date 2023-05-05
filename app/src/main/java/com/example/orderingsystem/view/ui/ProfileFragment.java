package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.orderingsystem.databinding.FragmentProfileBinding;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.UserRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseUserService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.UserViewModel;
import com.google.firebase.database.FirebaseDatabase;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    @Inject
    public AuthViewModel authViewModel;
    @Inject
    public UserViewModel userViewModel;

    private FragmentProfileBinding binding;
    private static ProfileFragment instance;

    private ProfileFragment() {
        // Required empty public constructor
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
                getActivity().finish();
//                startActivity(new Intent(getActivity(), SignInActivity.class));
            }
        });
    }

    private void signUserOut() {
        authViewModel.signOut();
    }
}