package com.example.orderingsystem.model.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.GeneralOrder;
import com.example.orderingsystem.model.data.GeneralUser;
import com.example.orderingsystem.model.data.Ordering;
import com.example.orderingsystem.model.data.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FirebaseOrderService implements FirebaseAPI<Ordering> {

    private final DatabaseReference reference;
    private final MutableLiveData<List<Ordering>> orderListMutable;
    private final MutableLiveData<Ordering> orderMutable;

    public FirebaseOrderService(DatabaseReference reference) {
        this.reference = reference;
        orderListMutable = new MutableLiveData<>();
        orderMutable = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<Ordering>> getAll(String key) {

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<Ordering> userList = new ArrayList<>();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    userList.add(snap.getValue(GeneralOrder.class));
                }
                orderListMutable.postValue(userList);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return orderListMutable;
    }

    @Override
    public LiveData<Ordering> getById(String id, String key) {

        reference.child(key).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                orderMutable.postValue(snapshot.getValue(GeneralOrder.class));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return orderMutable;
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
    public void write(Ordering obj, String key) {
        reference.child(key).setValue(obj);
    }
}
