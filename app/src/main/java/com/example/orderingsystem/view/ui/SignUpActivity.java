package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;
import com.example.orderingsystem.R;
import com.example.orderingsystem.model.data.GeneralUser;
import com.example.orderingsystem.model.data.Item;
import com.example.orderingsystem.model.data.User;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.RepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseService;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.example.orderingsystem.viewmodel.MainViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class SignUpActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;
    private MainViewModel mainViewModel;
    private LifecycleOwner lifecycleOwner;
    private Button signUpButton;
    private EditText email, password, confirmPassword, name, surname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setup();
        signUp();
    }

    private void setup() {
        lifecycleOwner = this;

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        authViewModel = new AuthViewModel(new AuthRepositoryImpl(new FirebaseAuthService(reference)));

        signUpButton = findViewById(R.id.buttonSignUp);
        name = findViewById(R.id.editTextName);
        surname = findViewById(R.id.editTextSurname);
        password = findViewById(R.id.editTextPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        email = findViewById(R.id.editTextEmail);
    }

    private void signUp() {

        String mName = name.getText().toString().trim();
        String mSurname = surname.getText().toString().trim();
        String mEmail = email.getText().toString().trim();
        String mPassword = password.getText().toString().trim();
        String mConfirmPassword = confirmPassword.getText().toString().trim();

        if (checkNull(mName, mSurname, mEmail, mPassword, mConfirmPassword)) return;

        if (checkConfirmPassword(mPassword, mConfirmPassword)) return;

        User userData = new GeneralUser();
        userData.setName(mName);
        userData.setSurname(mSurname);
        userData.setUserId(getRandomId());
        userData.setCredit(0f);
        userData.setAuthType(false);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authViewModel.createUserWithEmailPassword(mEmail, mPassword);
                authViewModel.getCurrentUser().observe(lifecycleOwner, user -> {
                    if (user != null) {
                        authViewModel.write(userData, String.valueOf(userData.getUserId()));
                    } else {
                        Toast.makeText(SignUpActivity.this, "Cannot sign up, please try again later.", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                });
            }
        });
    }

    private boolean checkNull(String...str) {
        for (String value: str) {
            if (value == null || value.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkConfirmPassword(String password, String confirmPassword) {
        return !password.equals(confirmPassword);
    }

    private int getRandomId() {
        return new Random().nextInt();
    }
}