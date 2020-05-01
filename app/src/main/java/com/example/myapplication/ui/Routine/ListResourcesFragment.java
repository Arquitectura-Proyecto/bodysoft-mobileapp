package com.example.myapplication.ui.Routine;

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
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardResource;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardRoutine;
import com.google.gson.internal.bind.util.ISO8601Utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListResourcesFragment extends Fragment {
    private RoutineStore routineStore;
    private Button goToRegisterResource;
    private RecyclerView resourceRecycler;
    private NavController navController;
    public ListResourcesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_resources, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.routineStore= ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        //listaRutinas=new ArrayList<>();
        this.goToRegisterResource=(Button)view.findViewById(R.id.btnGotoResourceRegister);
        this.resourceRecycler=(RecyclerView)view.findViewById(R.id.recyclerListResource);
        this.navController= Navigation.findNavController(view);
        this.goToRegisterResource.setOnClickListener(new btnGoToRegisterResourceListener());
        routineStore.getListResources().observe(getViewLifecycleOwner(), new Observer<List<GetResourcesByIdRoutineMutation.Resource>>() {
            @Override
            public void onChanged(List<GetResourcesByIdRoutineMutation.Resource> resources) {
                resourceRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterCardResource adapter=new AdapterCardResource(routineStore.getListResources().getValue(),navController,getContext());
                resourceRecycler.setAdapter(adapter);
            }
        });
        RoutineModel.getResourcesByIdRoutine(new getResourcesByIdRoutineListener(),Integer.parseInt(routineStore.getInformationRoutine().getValue().getId()),GlobalState.getToken());









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
    private class btnGoToRegisterResourceListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            navController.navigate(R.id.fromListResourcesFragment_to_registerResourceFragment);
        }
    }

}
