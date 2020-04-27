package com.example.myapplication.ui.Routine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Model.Entities.RoutineEntity;
import com.example.myapplication.Model.Entities.TypeRoutineEntity;
import com.example.myapplication.R;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardRoutine;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootRoutineFragment extends Fragment {
    private RecyclerView routineRecycler;
    private List<RoutineEntity> listaRutinas;
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
        listaRutinas=new ArrayList<>();
        routineRecycler=(RecyclerView)view.findViewById(R.id.recyclerListRoutine);
        routineRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Test();
        AdapterCardRoutine adapter=new AdapterCardRoutine(listaRutinas,this.getContext());
        routineRecycler.setAdapter(adapter);

    }
    private void Test(){
        RoutineEntity routine=new RoutineEntity();
        routine.setDescription("Descripcion de prueba");
        routine.setType(new TypeRoutineEntity(1,"Calistenia"));
        routine.setName("Rutina de Brazos");
        routine.setLink_preview("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4");
        listaRutinas.add(routine);
        listaRutinas.add(routine);
    }
}
