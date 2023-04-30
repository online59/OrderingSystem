package com.example.orderingsystem.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingsystem.databinding.OrderCardBinding;
import org.jetbrains.annotations.NotNull;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    private OrderCardBinding binding;

    public OrderViewHolder(OrderCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ImageView getMaterialImage() {
        return binding.ivItem;
    }

    public TextView getMaterialName() {
        return binding.tvName;
    }

    public TextView getMaterialPrice() {
        return binding.tvPrice;
    }

    public TextView getQuantity() {
        return binding.tvQuantity;
    }
}
