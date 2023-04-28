package com.example.orderingsystem.view.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;
import com.example.orderingsystem.R;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.view.MainActivity;
import com.example.orderingsystem.viewmodel.AuthViewModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;
    private LifecycleOwner lifecycleOwner;
    private Button signInButton, signUpButton;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setup();
        signIn();
        signUp();
    }

    private void setup() {
        lifecycleOwner = this;

        authViewModel = new AuthViewModel(new AuthRepositoryImpl(new FirebaseAuthService(null)));

        signInButton = findViewById(R.id.buttonSignIn);
        signUpButton = findViewById(R.id.buttonSignUp);

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
    }

    private void signIn() {

        if (checkNull(email.getText().toString().trim()) || checkNull(password.getText().toString().trim())) {
            return;
        }

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authViewModel.signInWithEmailPassword(email.getText().toString().trim(), password.getText().toString().trim());
                authViewModel.getCurrentUser().observe(lifecycleOwner, user -> {
                    if (user != null) {
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignInActivity.this, "Cannot log in, please try again later.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void signUp() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkNull(String str) {
        return str == null || str.isEmpty();
    }
}