package com.example.orderingsystem.view.ui;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;
import com.example.orderingsystem.R;
import com.example.orderingsystem.viewmodel.AuthViewModel;

public class SignUpActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;
    private Button signInButton, signUpButton;
    private LifecycleOwner lifecycleOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setup();
    }

    private void setup() {

    }
}