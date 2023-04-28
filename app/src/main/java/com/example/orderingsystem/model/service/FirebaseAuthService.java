package com.example.orderingsystem.model.service;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.GeneralUser;
import com.example.orderingsystem.model.data.Item;
import com.example.orderingsystem.model.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FirebaseAuthService {

    private final DatabaseReference reference;
    private final FirebaseAuth firebaseAuth;
    private MutableLiveData<FirebaseUser> currentUser;

    public FirebaseAuthService(@Nullable DatabaseReference reference) {
        this.reference = reference;
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

    public void write(User obj, String key) {
        reference.child(key).setValue(obj);
    }
}
