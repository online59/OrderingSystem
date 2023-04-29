package com.example.orderingsystem.model.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.GeneralUser;
import com.example.orderingsystem.model.data.ShopItem;
import com.example.orderingsystem.model.data.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUserService implements FirebaseAPI<User> {

    private DatabaseReference reference;
    private final MutableLiveData<List<User>> userListMutable;
    private final MutableLiveData<User> userMutable;

    public FirebaseUserService(DatabaseReference reference) {
        this.reference = reference;
        userListMutable = new MutableLiveData<>();
        userMutable = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<User>> getAll(String key) {

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    userList.add(snap.getValue(GeneralUser.class));
                }
                userListMutable.postValue(userList);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return userListMutable;
    }

    @Override
    public LiveData<User> getById(String id, String key) {

        reference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                userMutable.postValue(snapshot.getValue(GeneralUser.class));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return userMutable;
    }

    @Override
    public void removeAll(String key) {
        reference.child(key).removeValue();
    }

    @Override
    public void removeById(String id, String key) {
        reference.child(key).child(id).removeValue();
    }

    @Override
    public void write(User obj, String key) {
        reference.child(key).setValue(obj);
    }
}
