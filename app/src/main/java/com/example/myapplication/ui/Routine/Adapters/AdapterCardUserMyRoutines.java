package com.example.myapplication.ui.Routine.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apollographqlandroid.GetUserRoutineByIdUserQuery;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;

import java.util.List;

public class AdapterCardUserMyRoutines extends RecyclerView.Adapter<AdapterCardUserMyRoutines.ViewHolderCardUserMyRoutines> {

    private List<GetUserRoutineByIdUserQuery.UserRoutine> userRoutinesList;
    NavController navController;
    private Context context;

    public AdapterCardUserMyRoutines(List<GetUserRoutineByIdUserQuery.UserRoutine> userRoutinesList, NavController navController, Context context) {
        this.userRoutinesList = userRoutinesList;
        this.navController = navController;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterCardUserMyRoutines.ViewHolderCardUserMyRoutines onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user_my_routines,parent,false);
        //view.setOnClickListener(this);
        return new ViewHolderCardUserMyRoutines(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCardUserMyRoutines.ViewHolderCardUserMyRoutines holder, int position) {
        holder.txtTitle.setText(this.userRoutinesList.get(position).getRoutine().getName());
        System.out.println("user routines:"+this.userRoutinesList.toString());
        holder.btnVer.setOnClickListener(new btnVerListener(this.userRoutinesList.get(position)));
    }

    @Override
    public int getItemCount() {
        return this.userRoutinesList.size();
    }

    public class ViewHolderCardUserMyRoutines extends RecyclerView.ViewHolder {
       TextView txtTitle;
       Button btnVer;
        public ViewHolderCardUserMyRoutines(@NonNull View itemView) {
            super(itemView);
            this.txtTitle=(TextView)itemView.findViewById(R.id.txtTitleUserMyRoutine);
            this.btnVer=(Button)itemView.findViewById(R.id.btnViewRoutine);
        }
    }


    private class btnVerListener implements View.OnClickListener{

        private  GetUserRoutineByIdUserQuery.UserRoutine userRoutine;
        public btnVerListener(GetUserRoutineByIdUserQuery.UserRoutine userRoutine){
            this.userRoutine=userRoutine;
        }
        @Override
        public void onClick(View v) {
            RoutineStore routineStore= ViewModelProviders.of((FragmentActivity) context).get(RoutineStore.class);
            routineStore.setUserRoutine(userRoutine);
            navController.navigate(R.id.action_userMyRoutinesListFragment_to_userMyResourcesFragment);
        }
    }
}
