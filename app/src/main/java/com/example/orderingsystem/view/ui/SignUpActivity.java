package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.orderingsystem.databinding.ActivitySignUpBinding;
import com.example.orderingsystem.model.data.User;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.UserRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseUserService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.MyUtils;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;
import dagger.hilt.android.AndroidEntryPoint;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@AndroidEntryPoint
public class SignUpActivity extends AppCompatActivity {

    @Inject
    public AuthViewModel authViewModel;
    @Inject
    public UserViewModel userViewModel;

    private ActivitySignUpBinding binding;
    private String mName;
    private String mSurname;
    private String mEmail;
    private String mPassword;
    private String mConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        signUp();
    }

    private void signUp() {

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getUserInputInfo();
                createUserIdentityAndSaveToFirebase();

            }
        });
    }

    private void createUserIdentityAndSaveToFirebase() {

        if (MyUtils.isFieldsNull(mName, mSurname, mEmail, mPassword, mConfirmPassword)) {
            Toast.makeText(SignUpActivity.this, "Cannot create your account, please try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (MyUtils.isStringMatch(mPassword, mConfirmPassword)) {
            Toast.makeText(SignUpActivity.this, "Cannot create your account, please try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        authViewModel.createUserWithEmailPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    userViewModel.write(createUserData(getCurrentUserUid()),  getCurrentUserPath());

                    Toast.makeText(SignUpActivity.this, "Sign up successfully.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SignUpActivity.this, "Cannot sign up, please try again later.", Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });
    }

    private String getCurrentUserUid() {
        return authViewModel.getCurrentUser().getUid();
    }

    private String getCurrentUserPath() {
        return MyUtils.addItemsWithSlashSeparator(FirebasePath.PATH_USER, getCurrentUserUid());
    }

    private void getUserInputInfo() {
        mName = binding.editTextName.getText().toString().trim();
        mSurname = binding.editTextSurname.getText().toString().trim();
        mEmail = binding.editTextEmail.getText().toString().trim();
        mPassword = binding.editTextPassword.getText().toString().trim();
        mConfirmPassword = binding.editTextConfirmPassword.getText().toString().trim();
    }

    private User createUserData(String uid) {

        User userData = new User();
        userData.setName(mName);
        userData.setSurname(mSurname);
        userData.setEmail(mEmail);
        userData.setUserId(uid);
        userData.setCredit(MyUtils.getRandomFloat());
        userData.setAuthType(false);

        return userData;
    }
}