package com.example.myapplication.ui.Routine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apollographqlandroid.DeleteRequestMutation;
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.myapplication.Model.Entities.UserRoutineEntity;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestInformationFragment extends Fragment {
    private Button btnAcceptRequest;
    private Button btnRechazarRequest;
    private RoutineStore routineStore;
    public RequestInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.routineStore= ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        this.btnAcceptRequest=(Button)view.findViewById(R.id.btnAcceptRequest);

        this.btnRechazarRequest=(Button)view.findViewById(R.id.btnRechazarRequest);
        this.btnAcceptRequest.setOnClickListener(new btnAcceptRequestListener());
        this.btnRechazarRequest.setOnClickListener(new btnRejectRequestListener());

    }

    private class btnAcceptRequestListener implements View.OnClickListener{
        private int DefaultCreatedStatusUserRoutine=1;
        @Override
        public void onClick(View v) {
            UserRoutineEntity userRoutine=new UserRoutineEntity();
            userRoutine.setIdRoutine(Integer.parseInt(routineStore.getInformationRoutine().getValue().getId()));
            userRoutine.setIdStatus(DefaultCreatedStatusUserRoutine);
            userRoutine.setIdUser(routineStore.getInformationRequest().getValue().idUser());


            RoutineModel.acceptRequest(userRoutine,Integer.parseInt(routineStore.getInformationRequest().getValue().id()), GlobalState.getToken());
        }
    }


    private class btnRejectRequestListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            RoutineModel.rejectRequest(Integer.parseInt(routineStore.getInformationRequest().getValue().id()));
        }
    }
}
