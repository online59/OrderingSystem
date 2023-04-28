package com.example.orderingsystem.model.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public abstract class Item {

    @SerializedName("item_id")
    private int itemId;
    @SerializedName("item_name")
    private String itemName;
    @SerializedName("produce_year")
    private String produceYear;
    @SerializedName("description")
    private String description;
    @SerializedName("remaining")
    private int remaining;
    @SerializedName("price")
    private float price;
}
