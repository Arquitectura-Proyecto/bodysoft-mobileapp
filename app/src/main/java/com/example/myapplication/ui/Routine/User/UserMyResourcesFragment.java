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

import com.example.apollographqlandroid.GetResourcesByIdRoutineMutation;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardUserMyRoutines;

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
                 /*recyclerListResources.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterCardUserMyRoutines adapter=new AdapterCardUserMyRoutines(routineStore.getListResources().getValue(),navController,getContext());
                resourceRecycler.setAdapter(adapter);*/
            }
        });
        /*RoutineModel.getResourcesByIdRoutine(new getResourcesByIdRoutineListener(),Integer.parseInt(routineStore.getInformationRoutine().getValue().getId()),GlobalState.getToken());
*/







    }
}
