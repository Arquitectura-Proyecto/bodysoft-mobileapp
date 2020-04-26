package com.example.myapplication.ui.Routine;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.GetRoutinesQuery;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Models.ModelListener;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListRoutineFragment extends Fragment {
    private ListView listview;
    private ArrayAdapter<String>adaptador;
    GlobalState globalState;
    private TextView txt;
    public ListRoutineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_routine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        globalState= ViewModelProviders.of(getActivity()).get(GlobalState.class);
        txt=(TextView)view.findViewById(R.id.txtlistarutinas);
        listview=(ListView)view.findViewById(R.id.list_routines);
       /* ModelListener<List<GetRoutinesQuery.GetAllRoutine>>mylistener=new ModelListener<List<GetRoutinesQuery.GetAllRoutine>>() {
            @Override
            public void onSuccess(List<GetRoutinesQuery.GetAllRoutine> data) {
                ArrayList<String>nombres=new ArrayList<>();
                for(GetRoutinesQuery.GetAllRoutine s : data){
                    nombres.add(s.name());
                }
                adaptador=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,nombres);
                //listview.setAdapter(adaptador);
                //txt.setText("sirvio");



            }

            @Override
            public void onError(Exception e) {
               // Toast.makeText(getContext(),"Hubo un error"+ e.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.println("el error fue "+e.getMessage());
            }
        };*/
        RoutineModel.getAllRoutines(new ApolloCall.Callback<GetRoutinesQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetRoutinesQuery.Data> response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {
                        txt.setText(response.data().getAllRoutines().get(0).name());
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });


    }



}
