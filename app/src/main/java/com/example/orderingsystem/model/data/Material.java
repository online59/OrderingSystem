package com.example.orderingsystem.model.data;

import com.example.orderingsystem.database.entity.MaterialEntity;
import com.google.firebase.database.Exclude;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class Material {

    @SerializedName("item_id")
    private String itemId;
    @SerializedName("item_name")
    private String itemName;
    @SerializedName("produce_year")
    private int produceYear;
    @SerializedName("description")
    private String description;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("price")
    private float price;
    @SerializedName("weight")
    private float weight;
    @SerializedName("dimension")
    private float[] dimension;
    @SerializedName("variant")
    private String[] variant;

    @Exclude
    public List<MaterialEntity> asDatabaseModel(List<Material> materialList) {
        return materialList.stream().map(material -> {
            MaterialEntity entity = new MaterialEntity();
            entity.setItemId(material.itemId);
            entity.setItemName(material.itemName);
            entity.setProduceYear(material.produceYear);
            entity.setDescription(material.description);
            entity.setRemaining(material.quantity);
            entity.setPrice(material.price);
            return entity;
        }).collect(Collectors.toList());
    }
}
