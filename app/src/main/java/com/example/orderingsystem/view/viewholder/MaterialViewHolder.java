package com.example.orderingsystem.view.viewholder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingsystem.R;
import com.example.orderingsystem.view.event.ItemClickListener;
import org.jetbrains.annotations.NotNull;

public class MaterialViewHolder extends RecyclerView.ViewHolder {

    private final TextView itemName, itemDescription, itemPrice;

    public MaterialViewHolder(@NonNull @NotNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);

        itemName = itemView.findViewById(R.id.tv_name);
        itemDescription = itemView.findViewById(R.id.tv_description);
        itemPrice = itemView.findViewById(R.id.tv_price);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.setOnItemClick(getAdapterPosition());
            }
        });
    }

    public TextView getItemName() {
        return itemName;
    }

    public TextView getItemDescription() {
        return itemDescription;
    }

    public TextView getItemPrice() {
        return itemPrice;
    }
}
