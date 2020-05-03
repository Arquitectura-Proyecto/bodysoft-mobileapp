package com.example.myapplication.ui.Routine.User;
import android.drm.DrmStore;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.GetUserRoutineByIdUserQuery;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardResource;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardUserMyRoutines;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserMyRoutinesListFragment extends Fragment {
    private RecyclerView recyclerListUserRoutines;
    private RoutineStore routineStore;
    private NavController navController;
    public UserMyRoutinesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_my_routines_list, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.recyclerListUserRoutines = (RecyclerView) view.findViewById(R.id.recyclerListUserRoutines);
        this.routineStore=ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        this.navController=Navigation.findNavController(view);
        this.routineStore.getUserRoutineList().observe(getViewLifecycleOwner(), new Observer<List<GetUserRoutineByIdUserQuery.UserRoutine>>() {
            @Override
            public void onChanged(List<GetUserRoutineByIdUserQuery.UserRoutine> userRoutines) {
                recyclerListUserRoutines.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterCardUserMyRoutines adapter=new AdapterCardUserMyRoutines(routineStore.getUserRoutineList().getValue(),navController,getContext());
                recyclerListUserRoutines.setAdapter(adapter);
            }
        });
        RoutineModel.getRoutinesByIdUser(new getUserRoutinesByIdUserListener(), GlobalState.getToken());

    }
    private  class getUserRoutinesByIdUserListener extends ApolloCall.Callback<GetUserRoutineByIdUserQuery.Data>{


        @Override
        public void onResponse(@NotNull Response<GetUserRoutineByIdUserQuery.Data> response) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("los resultados son "+response.errors());
                    routineStore.setUserRoutineList(response.data().UserRoutines());
                }
            });
        }

        @Override
        public void onFailure(@NotNull ApolloException e) {

        }
    }
}
//