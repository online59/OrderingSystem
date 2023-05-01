package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.FragmentStoreBinding;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.data.User;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.UserRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseUserService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.MainViewModel;
import com.google.firebase.database.FirebaseDatabase;

public class StoreFragment extends Fragment {

    private FragmentStoreBinding binding;
    private static StoreFragment instance;
    private AuthViewModel authViewModel;
    private MainViewModel<User> userViewModel;

    private StoreFragment() {
    }

    public static StoreFragment getInstance() {
         if (instance == null) {
             instance = new StoreFragment();
         }
         return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = new AuthViewModel(new AuthRepositoryImpl(new FirebaseAuthService()));
        userViewModel = new MainViewModel<>(new UserRepositoryImpl(new FirebaseUserService(FirebaseDatabase.getInstance().getReference())));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStoreBinding.inflate(inflater, container, false);

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
                startActivity(new Intent(getActivity(), SignInActivity.class));
            }
        });
    }

    private void signUserOut() {
        authViewModel.signOut();
    }
}