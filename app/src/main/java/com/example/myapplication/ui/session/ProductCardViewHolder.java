package com.example.myapplication.ui.session;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {

    public TextView productTitle;
    public TextView productSubtitle;
    public TextView productSubtitle2;
    public MaterialButton button;

    public ProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        productTitle = itemView.findViewById(R.id.product_title);
        productSubtitle = itemView.findViewById(R.id.product_subtitle);
        productSubtitle2 = itemView.findViewById(R.id.product_subtitle2);
        button = itemView.findViewById(R.id.botonOpction);
    }
}
