package com.example.orderingsystem.view.viewholder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingsystem.R;
import com.example.orderingsystem.databinding.MaterialCardBinding;
import com.example.orderingsystem.utils.ItemClickListener;
import org.jetbrains.annotations.NotNull;

public class MaterialViewHolder extends RecyclerView.ViewHolder {

    private MaterialCardBinding binding;

    public MaterialViewHolder(MaterialCardBinding binding, ItemClickListener itemClickListener) {
        super(binding.getRoot());
        this.binding = binding;

        binding.getRoot().setOnClickListener(v -> itemClickListener.setOnItemClick(getAdapterPosition()));
    }

    public TextView getItemName() {
        return binding.itemName;
    }

    public TextView getItemPrice() {
        return binding.price;
    }
}
