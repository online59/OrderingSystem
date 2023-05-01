package com.example.orderingsystem.model.data;

import com.google.firebase.database.Exclude;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class User {

    @SerializedName("user_id")
    private String userId;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("email")
    private String email;
    @SerializedName("credit")
    private float credit;
    @SerializedName("auth_type")
    private boolean authType;

    @Exclude
    public String getFullName() {
        return name + " " + surname;
    }

    @Exclude
    public boolean isAdmin() {
        return authType;
    }
}
