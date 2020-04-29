package com.example.myapplication.ui.session;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter used to show a simple grid of products.
 */
public class CurrentCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    private List<Session> productList;

    CurrentCardRecyclerViewAdapter(List<Session> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            Session product = productList.get(position);
            holder.productTitle.setText(product.getDate());
            holder.productSubtitle.setText(product.getIniTime().substring(0,5));
            holder.productSubtitle2.setText(product.getEndTime());
            holder.button.setText("Cancelar");
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
