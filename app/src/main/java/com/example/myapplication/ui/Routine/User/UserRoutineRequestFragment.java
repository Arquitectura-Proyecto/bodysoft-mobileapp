package com.example.myapplication.ui.Routine.User;

import android.net.Uri;
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
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.CrearRutinaMutation;
import com.example.apollographqlandroid.GetRoutinesByIdTypeQuery;
import com.example.apollographqlandroid.RegisterRequestMutation;
import com.example.myapplication.Model.Entities.RoutineEntity;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.AuthGlobalState;
import com.example.myapplication.ui.GlobalState;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserRoutineRequestFragment extends Fragment {

    TextView txtTypeRoutineRoutineInformation;
    TextView txtNumRaitingRoutineInformation;
    TextView txtRaitingRoutineInformation;
    TextView txtDescriptionRoutineInformation;
    TextView txtPriceRoutineInformation;
    TextView txtNameRoutineInformation;
    Button btnRequestConfirmation;
    NavController navController;
    RoutineStore routineStore;
    private AuthGlobalState authGlobalState;
    public UserRoutineRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_routine_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        this.routineStore = ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        this.authGlobalState = ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);
        this.txtDescriptionRoutineInformation = (TextView) view.findViewById(R.id.txtDescriptionRoutineInformation);
        this.txtNameRoutineInformation = (TextView) view.findViewById(R.id.txtNameRoutineInformation);
        this.txtNumRaitingRoutineInformation = (TextView) view.findViewById(R.id.txtNumRaitingRoutineInformation);
        this.txtPriceRoutineInformation = (TextView) view.findViewById(R.id.txtPriceRoutineInformation);
        this.txtRaitingRoutineInformation = (TextView) view.findViewById(R.id.txtRaitingRoutineInformation);
        this.txtTypeRoutineRoutineInformation = (TextView) view.findViewById(R.id.txtTypeRoutineRoutineInformation);
        this.navController = Navigation.findNavController(view);


        this.btnRequestConfirmation = (Button) view.findViewById(R.id.btnRequestConfirmation);
        routineStore.getInformationRoutinePreview().observe(getViewLifecycleOwner(), new Observer<GetRoutinesByIdTypeQuery.Routine>() {
                    @Override
                    public void onChanged(GetRoutinesByIdTypeQuery.Routine routine) {
                        txtNameRoutineInformation.setText(routine.getName());
                        txtPriceRoutineInformation.setText(routine.getPrice() + "");
                        txtDescriptionRoutineInformation.setText(routine.getDescription());
                        txtRaitingRoutineInformation.setText(routine.getRaiting() + "");
                        txtNumRaitingRoutineInformation.setText(routine.getNumRaitings() + "");
                        txtTypeRoutineRoutineInformation.setText(routine.getType() + "");
                    }
                }
        );
        this.btnRequestConfirmation.setOnClickListener(new RequestConfirmationListener());

    }

    private class RequestConfirmationListener implements View.OnClickListener {
        RoutineStore routineStore;
        String routineId;
        @Override
        public void onClick(View v) {

            try {
                this.routineStore = ViewModelProviders.of(getActivity()).get(RoutineStore.class);
                this.routineId = "";
                routineStore.getInformationRoutinePreview().observe(getViewLifecycleOwner(), new Observer<GetRoutinesByIdTypeQuery.Routine>() {
                            @Override
                            public void onChanged(GetRoutinesByIdTypeQuery.Routine routine) {
                                routineId = routine.getId();
                            }
                        }
                );
                RoutineModel.registerRequest(new ApolloCall.Callback<RegisterRequestMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<RegisterRequestMutation.Data> response) {
                        System.out.println("routine request processed"+response.errors());
                        //Toast.makeText(v.getContext(),"routine request processed" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        System.out.println("error es "+e.getMessage());
                    }
                }, Integer.parseInt(routineId), authGlobalState.getToken().getValue());

            } catch (Exception e) {
                Toast.makeText(v.getContext(),
                        e.getMessage(), Toast.LENGTH_SHORT).show();////poner bien esto pues ocurre cuando el float no se puede castear
            }


        }
    }
}
