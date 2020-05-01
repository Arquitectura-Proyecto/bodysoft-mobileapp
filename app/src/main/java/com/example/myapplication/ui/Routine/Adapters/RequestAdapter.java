package com.example.myapplication.ui.Routine.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apollographqlandroid.GetRequestByIdRoutineQuery;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;

import java.util.List;

public class RequestAdapter   extends RecyclerView.Adapter<RequestAdapter.ViewHolderRequest> {

    List<GetRequestByIdRoutineQuery.Request> requestList;
    NavController navigationController;
    private Context context;

    public RequestAdapter(List<GetRequestByIdRoutineQuery.Request> requestList, NavController navigationController, Context context) {
        this.requestList = requestList;
        this.navigationController = navigationController;
        this.context = context;
    }

    @NonNull
    @Override
    public RequestAdapter.ViewHolderRequest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_request,parent,false);//parent para ajustarlo al layout del padre
        //view.setOnClickListener(this);
        return new ViewHolderRequest(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.ViewHolderRequest holder, int position) {
        holder.btnVer.setOnClickListener(new GoToRequestInformationFragmentLister(routineList.get(position)));
    }

    @Override
    public int getItemCount() {
        return this.requestList.size();
    }

    public class ViewHolderRequest extends RecyclerView.ViewHolder {

        private Button btnVer;

        public ViewHolderRequest(@NonNull View itemView) {
            super(itemView);
            this.btnVer=(Button) itemView.findViewById(R.id.btnViewRequest);
        }
    }
    private class GoToRequestInformationFragmentLister implements View.OnClickListener {
        private GetRequestByIdRoutineQuery.Request request;

        public GoToRequestInformationFragmentLister(GetRequestByIdRoutineQuery.Request request) {
            this.request = request;
        }

        @Override
        public void onClick(View v) {
            RoutineStore routineStore= ViewModelProviders.of((FragmentActivity) context).get(RoutineStore.class);
            //routineStore.setRoutineInformation();

            navigationController.navigate(R.id.fromRootRoutineFragment_to_routineInformationFragment);
        }
    }
}
