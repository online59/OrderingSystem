package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.orderingsystem.databinding.ActivitySignInBinding;
import com.example.orderingsystem.model.data.User;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.UserRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseUserService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.MyUtils;
import com.example.orderingsystem.view.AdminMainActivity;
import com.example.orderingsystem.view.MainActivity;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private AuthViewModel authViewModel;
    private UserViewModel userViewModel;
    private String mEmail;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setup();
        signUp();
        signIn();
    }

    private void setup() {
        authViewModel = new AuthViewModel(new AuthRepositoryImpl(new FirebaseAuthService()));
        userViewModel = new UserViewModel(new UserRepositoryImpl(new FirebaseUserService(FirebaseDatabase.getInstance().getReference())));
    }

    private void signUp() {

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }

    private void signIn() {
        binding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUserUpWithEmailPassword(getEmailPassword());
            }
        });
    }

    private void signUserUpWithEmailPassword(String[] signInData) {

        if (MyUtils.isFieldsNull(mEmail, mPassword)) {
            Toast.makeText(SignInActivity.this, "Cannot sign in, please try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        authViewModel.signInWithEmailPassword(signInData[0], signInData[1]).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    startActivityByUserRole(authViewModel.getCurrentUser().getUid());

                } else {

                    Toast.makeText(SignInActivity.this, "Cannot log in, please try again later.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private String[] getEmailPassword() {
        mEmail = binding.editTextEmail.getText().toString().trim();
        mPassword = binding.editTextPassword.getText().toString().trim();

        return new String[]{mEmail, mPassword};
    }

    private void startActivityByUserRole(String currentUserUid) {

        userViewModel.getById(currentUserUid, FirebasePath.PATH_USER).observe(this, user -> {

            if (user.isAdmin()) {

                startActivity(new Intent(SignInActivity.this, AdminMainActivity.class));

            } else {

                startActivity(new Intent(SignInActivity.this, MainActivity.class));
            }
        });
    }
}