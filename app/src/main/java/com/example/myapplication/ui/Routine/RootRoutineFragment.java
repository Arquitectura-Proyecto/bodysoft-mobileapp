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
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.myapplication.Model.Entities.RoutineEntity;
import com.example.myapplication.Model.Entities.TypeRoutineEntity;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardRoutine;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootRoutineFragment extends Fragment {

    RoutineStore routineStore;
    private RecyclerView routineRecycler;
    private Button goToRegisterRoutine;
    private RoutineModel routineModel;
    NavController navController;
    public RootRoutineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_root_routine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.routineStore= ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        //listaRutinas=new ArrayList<>();
        this.goToRegisterRoutine=(Button)view.findViewById(R.id.btnGotoRoutineRegister);
        routineRecycler=(RecyclerView)view.findViewById(R.id.recyclerListRoutine);
        routineStore.getListRoutine().observe(getViewLifecycleOwner(), new Observer<List<GetRoutinesByIdOwnerQuery.Routine>>() {
            @Override
            public void onChanged(List<GetRoutinesByIdOwnerQuery.Routine> routines) {
                routineRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                //Test();
                AdapterCardRoutine adapter=new AdapterCardRoutine(routineStore.getListRoutine().getValue(),getContext());
                routineRecycler.setAdapter(adapter);
            }
        });
        navController= Navigation.findNavController(view);
        this.goToRegisterRoutine=(Button)view.findViewById(R.id.btnGotoRoutineRegister);

        this.goToRegisterRoutine.setOnClickListener(new GoToRoutineRegisterListener());








        //Test();
        RoutineModel.getAllRoutinesByIdOwner(new getAllRotuinesByIdOwnerListener(), GlobalState.getToken());
      /*  routineRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //Test();
        AdapterCardRoutine adapter=new AdapterCardRoutine(listaRutinas,this.getContext());
        routineRecycler.setAdapter(adapter);
*/
    }

    private class getAllRotuinesByIdOwnerListener extends ApolloCall.Callback<GetRoutinesByIdOwnerQuery.Data>{


        @Override
        public void onResponse(@NotNull Response<GetRoutinesByIdOwnerQuery.Data> response) {


            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {
                    routineStore.setRoutines(response.data().Routines());
                }
            });
        }

        @Override
        public void onFailure(@NotNull ApolloException e) {

        }
    }
    private void Test(){
      /*  RoutineEntity routine=new RoutineEntity();
        routine.setDescription("Descripcion de prueba");
        routine.setType(new TypeRoutineEntity(1,"Calistenia"));
        routine.setName("Rutina de Brazos");
        routine.setLink_preview("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4");
        listaRutinas.add(routine);
        listaRutinas.add(routine);
        routineStore.setRoutines(listaRutinas);*/
        GetRoutinesByIdOwnerQuery.Routine routine=new GetRoutinesByIdOwnerQuery.Routine("CALISTENIA","1",2,2.3,3,3.4,"NOMBRE","dESCRIP","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",new GetRoutinesByIdOwnerQuery.GetType("sd","ds","sd"));

        List<GetRoutinesByIdOwnerQuery.Routine> listaRutinas=new ArrayList<>();
                listaRutinas.add(routine);
                routineStore.setRoutines(listaRutinas);
    }
    private class GoToRoutineRegisterListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            navController.navigate(R.id.fromRootRoutineToRegisterRoutine);
        }
    }
}
