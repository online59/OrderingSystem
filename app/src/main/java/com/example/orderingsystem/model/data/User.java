package com.example.orderingsystem.model.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public abstract class User {

    @SerializedName("user_id")
    private int userId;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("credit")
    private float credit;
    @SerializedName("auth_type")
    private boolean authType;

    public String getFullName() {
        return name + " " + surname;
    }

    public boolean isAdmin() {
        return authType;
    }
}
