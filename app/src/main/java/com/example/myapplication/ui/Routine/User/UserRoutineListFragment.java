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
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.apollographqlandroid.GetRoutinesByIdTypeQuery;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardSuggestedRoutine;
import com.jaredrummler.materialspinner.MaterialSpinner;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserRoutineListFragment extends Fragment {

    RoutineStore routineStore;
    private RecyclerView recyclerListMyRoutines;
    private Button btnGotoMyRoutinesList;
    private MaterialSpinner spinnerRoutineTypes;
    NavController navController;
    //recyclerListMyRoutines
    //btnGotoMyRoutinesList
    public UserRoutineListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_routine_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.routineStore = ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        this.btnGotoMyRoutinesList = (Button) view.findViewById(R.id.btnGotoMyRoutinesList);
        recyclerListMyRoutines = (RecyclerView) view.findViewById(R.id.recyclerListMyRoutines);
        navController = Navigation.findNavController(view);
        routineStore.getListSuggestedRoutines().observe(getViewLifecycleOwner(), new Observer<List<GetRoutinesByIdTypeQuery.Routine>>() {
            @Override
            public void onChanged(List<GetRoutinesByIdTypeQuery.Routine> routines) {
                recyclerListMyRoutines.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterCardSuggestedRoutine adapter=new AdapterCardSuggestedRoutine(routines,navController,getContext());
                recyclerListMyRoutines.setAdapter(adapter);
            }
        });
        this.spinnerRoutineTypes = (MaterialSpinner) view.findViewById(R.id.spinnerRoutineTypes);
        this.btnGotoMyRoutinesList.setOnClickListener(new GoToMyRoutinesListener());
       // RoutineModel.getAllRoutinesByIdOwner(new UserRoutineListFragment.getAllRotuinesByIdOwnerListener(), GlobalState.getToken());
        RoutineModel.getRoutinesByIdType(new getAllRoutinesByIdTypeListener(),1);

    }
    private class getAllRoutinesByIdTypeListener extends ApolloCall.Callback<GetRoutinesByIdTypeQuery.Data>{

        @Override
        public void onResponse(@NotNull Response<GetRoutinesByIdTypeQuery.Data> response) {
            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {
                    routineStore.setListSuggestedRoutines(response.data().Routines());
                }
            });
        }

        @Override
        public void onFailure(@NotNull ApolloException e) {

        }
    }
    private void Test(){
        GetRoutinesByIdOwnerQuery.Routine routine=new GetRoutinesByIdOwnerQuery.Routine("CALISTENIA","1",2,2.3,3,3.4,"NOMBRE","dESCRIP","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",new GetRoutinesByIdOwnerQuery.GetType("sd","ds","sd"));
        List<GetRoutinesByIdOwnerQuery.Routine> listaRutinas=new ArrayList<>();
        listaRutinas.add(routine);
        routineStore.setRoutines(listaRutinas);
    }
    private class GoToMyRoutinesListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            navController.navigate(R.id.action_userRoutineListFragment_to_userMyRoutinesListFragment);
        }
    }

}
