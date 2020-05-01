package com.example.myapplication.ui.Routine.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apollographqlandroid.GetRequestByIdRoutineQuery;

import java.util.List;

public class RequestAdapter   extends RecyclerView.Adapter<RequestAdapter.ViewHolderRequest> {

    List<GetRequestByIdRoutineQuery.Request> routineList;
    NavController navigationController;
    private Context context;

    public RequestAdapter(List<GetRequestByIdRoutineQuery.Request> routineList, NavController navigationController, Context context) {
        this.routineList = routineList;
        this.navigationController = navigationController;
        this.context = context;
    }

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
