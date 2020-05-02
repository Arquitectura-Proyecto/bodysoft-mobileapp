package com.example.myapplication.ui.Routine;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
///esta clase se puede eliminar
public class RoutineFragment extends Fragment {

    private RoutineViewModel mViewModel;
    GlobalState globalState;

    public static RoutineFragment newInstance() {
        return new RoutineFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.routine_fragment, container, false);
        globalState= ViewModelProviders.of(getActivity()).get(GlobalState.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText txtNombreRoutine=(EditText) view.findViewById(R.id.txtinputname);

        txtNombreRoutine.setText(globalState.getSharedString().getValue());
        Button registrar =(Button)view.findViewById(R.id.btnregistrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalState.setString(txtNombreRoutine.getText().toString());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
