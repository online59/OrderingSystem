package com.example.orderingsystem.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingsystem.databinding.OrderCardBinding;
import com.example.orderingsystem.utils.ItemClickListener;
import org.jetbrains.annotations.NotNull;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    private OrderCardBinding binding;

    public OrderViewHolder(OrderCardBinding binding, ItemClickListener itemClickListener) {
        super(binding.getRoot());
        this.binding = binding;

        binding.getRoot().setOnClickListener(view -> itemClickListener.setOnItemClick(getAdapterPosition()));
    }

    public ImageView getMaterialImage() {
        return binding.itemImage;
    }

    public TextView getMaterialName() {
        return binding.itemName;
    }

    public TextView getMaterialPrice() {
        return binding.price;
    }

    public TextView getQuantity() {
        return binding.tvQuantity;
    }
}
