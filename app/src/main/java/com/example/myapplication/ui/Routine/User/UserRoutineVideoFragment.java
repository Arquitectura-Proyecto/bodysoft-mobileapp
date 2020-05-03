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
import com.example.apollographqlandroid.GetResourcesByIdRoutineMutation;
import com.example.apollographqlandroid.GetRoutinesByIdTypeQuery;
import com.example.apollographqlandroid.RegisterRequestMutation;
import com.example.myapplication.Model.Entities.RoutineEntity;
import com.example.myapplication.Model.Models.RoutineModel;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserRoutineVideoFragment extends Fragment {

    TextView txtNameRoutineInformation;
    TextView txtDescriptionRoutineInformation;
    VideoView imgRoutine;
    RoutineStore routineStore;

    public UserRoutineVideoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_routine_video, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.routineStore = ViewModelProviders.of(getActivity()).get(RoutineStore.class);

        this.imgRoutine = (VideoView) view.findViewById(R.id.imgRoutine);
        this.txtDescriptionRoutineInformation = (TextView) view.findViewById(R.id.txtDescriptionRoutineInformation);
        this.txtNameRoutineInformation = (TextView) view.findViewById(R.id.txtNameRoutineInformation);

        routineStore.getResourceInformation().observe(getViewLifecycleOwner(), new Observer<GetResourcesByIdRoutineMutation.Resource>() {
            @Override
            public void onChanged(GetResourcesByIdRoutineMutation.Resource resource) {
                Uri uri = Uri.parse(resource.getLink());
                imgRoutine.setVideoURI(uri);
                imgRoutine.start();
                MediaController mediaController = new MediaController(getContext());
                mediaController.setAnchorView(imgRoutine);
                imgRoutine.setMediaController(mediaController);
                txtNameRoutineInformation.setText(resource.getTitle());

                txtDescriptionRoutineInformation.setText(resource.getDescription());
            }
        });

    }

}
