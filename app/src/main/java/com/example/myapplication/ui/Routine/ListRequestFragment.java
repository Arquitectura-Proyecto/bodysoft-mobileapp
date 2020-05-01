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

import com.example.apollographqlandroid.GetRequestByIdRoutineQuery;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.Routine.Adapters.RequestAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListRequestFragment extends Fragment {
    private RoutineStore routineStore;
    private RecyclerView recyclerRequest;
    private NavController navController;
    public ListRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.routineStore= ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        this.navController= Navigation.findNavController(view);
        this.recyclerRequest=(RecyclerView)view.findViewById(R.id.recyclerListRequest);
        this.routineStore.getListRequest().observe(getViewLifecycleOwner(), new Observer<List<GetRequestByIdRoutineQuery.Request>>() {
            @Override
            public void onChanged(List<GetRequestByIdRoutineQuery.Request> requests) {
                recyclerRequest.setLayoutManager(new LinearLayoutManager(getContext()));
                RequestAdapter adapter=new RequestAdapter(routineStore.getListRequest().getValue(),navController,getContext());
                recyclerRequest.setAdapter(adapter);
            }
        });


    }
}
