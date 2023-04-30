package com.example.orderingsystem.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingsystem.R;
import com.example.orderingsystem.model.data.Material;
import com.example.orderingsystem.view.event.ItemClickListener;
import com.example.orderingsystem.view.viewholder.MaterialViewHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialViewHolder>{

    private List<Material> materialList;
    private ItemClickListener itemClickListener;

    public MaterialAdapter() {
    }

    public void setShopItemList(List<Material> itemList) {
        this.materialList = itemList;
        notifyDataSetChanged();
    }

    public Material getShopItemList(int position) {
        return materialList.get(position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_card, parent, false);
        return new MaterialViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MaterialViewHolder holder, int position) {
        Material material = materialList.get(position);
        holder.getItemName().setText(material.getItemName());
        holder.getItemDescription().setText(material.getDescription());
        holder.getItemPrice().setText(String.valueOf(material.getPrice()));
    }

    @Override
    public int getItemCount() {
        return (materialList != null)? materialList.size() : 0 ;
    }
}
