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

import com.example.apollographqlandroid.GetResourcesByIdRoutineMutation;
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardResource;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardRoutine;

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
        /*routineStore.getListRoutine().observe(getViewLifecycleOwner(), new Observer<List<GetRoutinesByIdOwnerQuery.Routine>>() {
            @Override
            public void onChanged(List<GetRoutinesByIdOwnerQuery.Routine> routines) {
                routineRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                //Test();
                AdapterCardRoutine adapter=new AdapterCardRoutine(routineStore.getListRoutine().getValue(),getContext(),navController);
                //adapter.setOnClickListener();
                routineRecycler.setAdapter(adapter);
            }
        });

        this.goToRegisterRoutine=(Button)view.findViewById(R.id.btnGotoRoutineRegister);

        this.goToRegisterRoutine.setOnClickListener(new RootRoutineFragment.GoToRoutineRegisterListener());
*/
        routineStore.getListResources().observe(getViewLifecycleOwner(), new Observer<List<GetResourcesByIdRoutineMutation.Resource>>() {
            @Override
            public void onChanged(List<GetResourcesByIdRoutineMutation.Resource> resources) {
                resourceRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterCardResource adapter=new AdapterCardResource(routineStore.getListResources().getValue(),navController,getContext());
                resourceRecycler.setAdapter(adapter);
            }
        });









    }
}
