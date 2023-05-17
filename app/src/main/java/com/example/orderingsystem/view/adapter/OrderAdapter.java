package com.example.orderingsystem.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingsystem.databinding.OrderCardBinding;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.utils.ItemClickListener;
import com.example.orderingsystem.view.viewholder.OrderViewHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private OrderCardBinding binding;
    private ItemClickListener itemClickListener;
    private List<Order> orderList;
    private boolean isAdmin;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Order getOder(int position) {
        return orderList.get(position);
    }

    @NonNull
    @NotNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = OrderCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderViewHolder(binding, isAdmin, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.getMaterialName().setText(order.getItemName());
        holder.getQuantity().setText(String.valueOf(order.getQuantity()));
        holder.getMaterialPrice().setText(String.valueOf(order.getTotalPrice()));
    }

    @Override
    public int getItemCount() {
        return (orderList != null)? orderList.size() : 0;
    }
}
