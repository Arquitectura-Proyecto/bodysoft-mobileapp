package com.example.myapplication.ui.Routine.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RequestAdapter   extends RecyclerView.Adapter<RequestAdapter.ViewHolderRequest> {
    @NonNull
    @Override
    public RequestAdapter.ViewHolderRequest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.ViewHolderRequest holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderRequest extends RecyclerView.ViewHolder {
        public ViewHolderRequest(@NonNull View itemView) {
            super(itemView);
        }
    }
}
