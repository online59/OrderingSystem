package com.example.orderingsystem.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingsystem.databinding.MaterialCardBinding;
import com.example.orderingsystem.model.data.Material;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.view.viewholder.MaterialViewHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialViewHolder>{

    private MaterialCardBinding binding;

    private List<Material> materialList;
    private ItemClickListener itemClickListener;

    public MaterialAdapter() {
    }

    public void setShopItemList(List<Material> itemList) {
        this.materialList = itemList;
        notifyDataSetChanged();
    }

    public Material getMaterial(int position) {
        return materialList.get(position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = MaterialCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MaterialViewHolder(binding, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MaterialViewHolder holder, int position) {
        Material material = materialList.get(position);
        holder.getItemName().setText(material.getItemName());
        holder.getItemPrice().setText(String.valueOf(material.getPrice()));
    }

    @Override
    public int getItemCount() {
        return (materialList != null)? materialList.size() : 0 ;
    }
}
