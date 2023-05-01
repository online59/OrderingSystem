package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.ActivitySignUpBinding;
import com.example.orderingsystem.model.data.GeneralUser;
import com.example.orderingsystem.model.data.User;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.UserRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseUserService;
import com.example.orderingsystem.utils.FirebasePath;
import com.example.orderingsystem.utils.MyUtils;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.MainViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private AuthViewModel authViewModel;
    private MainViewModel<User> userViewModel;
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

        setup();
        signUp();
    }

    private void setup() {
        authViewModel = new AuthViewModel(new AuthRepositoryImpl(new FirebaseAuthService()));
        userViewModel = new MainViewModel<>(new UserRepositoryImpl(new FirebaseUserService(FirebaseDatabase.getInstance().getReference())));
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

                    userViewModel.write(createUserData(getFirebaseAuthUserUID()),  FirebasePath.PATH_USER + "/" + getFirebaseAuthUserUID());

                    Toast.makeText(SignUpActivity.this, "Sign up successfully.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SignUpActivity.this, "Cannot sign up, please try again later.", Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });
    }

    private String getFirebaseAuthUserUID() {
        return authViewModel.getCurrentUser().getUid();
    }

    private void getUserInputInfo() {
        mName = binding.editTextName.getText().toString().trim();
        mSurname = binding.editTextSurname.getText().toString().trim();
        mEmail = binding.editTextEmail.getText().toString().trim();
        mPassword = binding.editTextPassword.getText().toString().trim();
        mConfirmPassword = binding.editTextConfirmPassword.getText().toString().trim();
    }

    private User createUserData(String uid) {

        User userData = new GeneralUser();
        userData.setName(mName);
        userData.setSurname(mSurname);
        userData.setEmail(mEmail);
        userData.setUserId(uid);
        userData.setCredit(MyUtils.getRandomFloat());
        userData.setAuthType(false);

        return userData;
    }
}