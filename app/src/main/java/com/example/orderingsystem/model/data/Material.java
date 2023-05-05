package com.example.orderingsystem.model.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.orderingsystem.database.entity.MaterialEntity;
import com.google.firebase.database.Exclude;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.annotation.Nonnull;
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
    @SerializedName("remaining")
    private int remaining;
    @SerializedName("price")
    private float price;

    @Exclude
    public List<MaterialEntity> asDatabaseModel(List<Material> materialList) {
        return materialList.stream().map(material -> {
            MaterialEntity entity = new MaterialEntity();
            entity.setItemId(material.itemId);
            entity.setItemName(material.itemName);
            entity.setProduceYear(material.produceYear);
            entity.setDescription(material.description);
            entity.setRemaining(material.remaining);
            entity.setPrice(material.price);
            return entity;
        }).collect(Collectors.toList());
    }
}
