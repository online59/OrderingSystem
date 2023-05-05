package com.example.orderingsystem.model.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseUserService implements FirebaseAPI<User> {

    @Inject
    public DatabaseReference reference;
    private final MutableLiveData<List<User>> userListMutable;
    private final MutableLiveData<User> userMutable;

    @Inject
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
                    userList.add(snap.getValue(User.class));
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

        reference.child(key).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                userMutable.postValue(snapshot.getValue(User.class));
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

    @Override
    public void update(User obj, String key) {

        Map<String, Object> childUpdate = new HashMap<>();
        childUpdate.put(key, obj);

        reference.updateChildren(childUpdate);
    }
}
