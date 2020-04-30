package com.example.myapplication.ui.Routine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoutineInformationFragment extends Fragment {
    RoutineStore routineStore;
    private TextView txtNameRoutineInformation;
    private TextView txtPriceRoutineInformation;
    private TextView txtDescriptionRoutineInformation;
    private TextView txtRaitingRoutineInformation;
    private TextView txtNumRaitingRoutineInformation;
    private TextView txtTypeRoutineRoutineInformation;
    private Button btnGoToEditRoutine;
    private NavController navController;
    public RoutineInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_routine_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.routineStore= ViewModelProviders.of(getActivity()).get(RoutineStore.class);
         this.txtNameRoutineInformation=(TextView)view.findViewById(R.id.txtNameRoutineInformation);
         this.txtPriceRoutineInformation=(TextView) view.findViewById(R.id.txtPriceRoutineInformation);
         this.txtDescriptionRoutineInformation=(TextView)view.findViewById(R.id.txtDescriptionRoutineInformation);
         this.txtRaitingRoutineInformation=(TextView)view.findViewById(R.id.txtRaitingRoutineInformation);
         this.txtNumRaitingRoutineInformation=(TextView)view.findViewById(R.id.txtNumRaitingRoutineInformation);
         this.txtTypeRoutineRoutineInformation=(TextView)view.findViewById(R.id.txtTypeRoutineRoutineInformation);
         this.navController= Navigation.findNavController(view);
         this.btnGoToEditRoutine=(Button)view.findViewById(R.id.btnGoToEditRoutine);
         this.btnGoToEditRoutine.setOnClickListener(new btnGoToEditRoutineListener());

         this.routineStore.getInformationRoutine().observe(getViewLifecycleOwner(), new Observer<GetRoutinesByIdOwnerQuery.Routine>() {
            @Override
            public void onChanged(GetRoutinesByIdOwnerQuery.Routine routine) {
                txtNameRoutineInformation.setText(routine.getName());
                txtPriceRoutineInformation.setText(""+routine.getPrice());
                txtDescriptionRoutineInformation.setText(routine.getDescription());
                txtRaitingRoutineInformation.setText(""+routine.getRaiting());
                txtNumRaitingRoutineInformation.setText(""+routine.getNumRaitings());
                txtTypeRoutineRoutineInformation.setText(routine.getType().getName());

            }
        });
    }
    private class btnGoToEditRoutineListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            navController.navigate(R.id.fromRoutineInformationFragment_to_editRoutineFragment);
        }
    }
}
