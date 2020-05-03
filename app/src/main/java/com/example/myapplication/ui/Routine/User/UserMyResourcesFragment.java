package com.example.myapplication.ui.Routine.User;
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
import android.widget.Button;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.GetResourcesByIdRoutineMutation;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardResource;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardUserMyRoutines;
import com.example.myapplication.ui.Routine.Adapters.AdapterResourceList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
/**
 * A simple {@link Fragment} subclass.
 */
public class UserMyResourcesFragment extends Fragment {
    private RecyclerView recyclerListResources;
    private RoutineStore routineStore;
    private NavController navController;
    public UserMyResourcesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_my_routine, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.recyclerListResources= (RecyclerView) view.findViewById(R.id.recyclerListResourcesByUser);
        this.routineStore= ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        //listaRutinas=new ArrayList<>();


        this.navController= Navigation.findNavController(view);


        routineStore.getListResources().observe(getViewLifecycleOwner(), new Observer<List<GetResourcesByIdRoutineMutation.Resource>>() {
            @Override
            public void onChanged(List<GetResourcesByIdRoutineMutation.Resource> resources) {
                 recyclerListResources.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterResourceList adapter=new AdapterResourceList(routineStore.getListResources().getValue(),navController,getContext());
                recyclerListResources.setAdapter(adapter);
            }
        });
        RoutineModel.getResourcesByIdRoutine(new getResourcesByIdRoutineListener(),Integer.parseInt(routineStore.getUserRoutine().getValue().getRoutine().getId()), GlobalState.getToken());








    }

    private class getResourcesByIdRoutineListener extends ApolloCall.Callback<GetResourcesByIdRoutineMutation.Data>{


        @Override
        public void onResponse(@NotNull Response<GetResourcesByIdRoutineMutation.Data> response) {

            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {
                    routineStore.setListResources(response.data().Resources());
                }
            });

        }

        @Override
        public void onFailure(@NotNull ApolloException e) {

            e.printStackTrace();
        }
    }
}

