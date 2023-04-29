package com.example.orderingsystem.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingsystem.R;
import com.example.orderingsystem.model.data.ShopItem;
import com.example.orderingsystem.view.event.ItemClickListener;
import com.example.orderingsystem.view.viewholder.ShopItemViewHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemViewHolder>{

    private List<ShopItem> shopItemList;
    private ItemClickListener itemClickListener;

    public ShopItemAdapter() {
    }

    public void setShopItemList(List<ShopItem> itemList) {
        this.shopItemList = itemList;
        notifyDataSetChanged();
    }

    public ShopItem getShopItemList(int position) {
        return shopItemList.get(position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ShopItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ShopItemViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ShopItemViewHolder holder, int position) {
        ShopItem shopItem = shopItemList.get(position);
        holder.getItemName().setText(shopItem.getItemName());
        holder.getItemDescription().setText(shopItem.getDescription());
        holder.getItemPrice().setText(String.valueOf(shopItem.getPrice()));
    }

    @Override
    public int getItemCount() {
        return (shopItemList != null)? shopItemList.size() : 0 ;
    }
}
