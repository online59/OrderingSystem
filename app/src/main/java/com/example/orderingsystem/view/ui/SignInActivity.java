package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.orderingsystem.databinding.ActivitySignInBinding;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.MyUtils;
import com.example.orderingsystem.view.AdminMainActivity;
import com.example.orderingsystem.view.MainActivity;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import dagger.hilt.android.AndroidEntryPoint;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@AndroidEntryPoint
public class SignInActivity extends AppCompatActivity {

    @Inject
    public AuthViewModel authViewModel;
    @Inject
    public UserViewModel userViewModel;

    private ActivitySignInBinding binding;
    private String mEmail;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        signUp();
        signIn();
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

        int username = 0;
        int password = 1;

        authViewModel.signInWithEmailPassword(signInData[username], signInData[password]).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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