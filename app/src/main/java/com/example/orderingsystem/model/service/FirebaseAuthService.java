package com.example.orderingsystem.model.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.jetbrains.annotations.NotNull;

public class FirebaseAuthService{

    private final FirebaseAuth firebaseAuth;
    private MutableLiveData<FirebaseUser> currentUser;

    public FirebaseAuthService() {
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = new MutableLiveData<>();
    }

    public void signInWithEmailPassword(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    currentUser.postValue(firebaseAuth.getCurrentUser());
                } else {
                    currentUser.setValue(null);
                }
            }
        });
    }


    public void createUserWithEmailPassword(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    currentUser.postValue(firebaseAuth.getCurrentUser());
                } else {
                    currentUser.setValue(null);
                }
            }
        });
    }


    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }
}
