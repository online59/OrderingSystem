package com.example.orderingsystem.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingsystem.R;
import com.example.orderingsystem.model.data.Item;
import com.example.orderingsystem.view.event.ItemClickListener;
import com.example.orderingsystem.view.viewholder.ItemViewHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>{

    private List<Item> itemList;
    private ItemClickListener itemClickListener;

    public ItemAdapter() {
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ItemViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.getItemName().setText(item.getItemName());
        holder.getItemDescription().setText(item.getDescription());
        holder.getItemPrice().setText(String.valueOf(item.getPrice()));
    }

    @Override
    public int getItemCount() {
        return (itemList != null)? itemList.size() : 0 ;
    }
}
