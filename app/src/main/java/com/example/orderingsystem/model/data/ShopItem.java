package com.example.orderingsystem.model.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public abstract class ShopItem {

    @SerializedName("item_id")
    private String itemId;
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
