package com.example.myapplication.ui.Routine.User;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.apollographqlandroid.GetRoutinesByIdTypeQuery;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.Routine.Adapters.AdapterCardSuggestedRoutine;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserRoutinePreviewFragment extends Fragment {
    VideoView imgRoutine;
    TextView txtNameRoutineInformation;
    TextView txtPriceRoutineInformation;
    TextView txtDescriptionRoutineInformation;
    TextView txtRaitingRoutineInformation;
    TextView txtNumRaitingRoutineInformation;
    TextView txtTypeRoutineRoutineInformation;
    Button btnRequestRoutine;
    RoutineStore routineStore;
    NavController navController;


    public UserRoutinePreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_routine_preview, container, false);
    }

    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.routineStore = ViewModelProviders.of(getActivity()).get(RoutineStore.class);
        this.btnRequestRoutine = (Button) view.findViewById(R.id.btnRequestRoutine);
        this.imgRoutine = (VideoView) view.findViewById(R.id.imgRoutine);
        this.txtDescriptionRoutineInformation = (TextView) view.findViewById(R.id.txtDescriptionRoutineInformation);
        this.txtNameRoutineInformation = (TextView) view.findViewById(R.id.txtNameRoutineInformation);
        this.txtNumRaitingRoutineInformation = (TextView) view.findViewById(R.id.txtNumRaitingRoutineInformation);
        this.txtPriceRoutineInformation = (TextView) view.findViewById(R.id.txtPriceRoutineInformation);
        this.txtRaitingRoutineInformation = (TextView) view.findViewById(R.id.txtRaitingRoutineInformation);
        this.txtTypeRoutineRoutineInformation = (TextView) view.findViewById(R.id.txtTypeRoutineRoutineInformation);
        this.navController = Navigation.findNavController(view);
        routineStore.getInformationRoutinePreview().observe(getViewLifecycleOwner(), new Observer<GetRoutinesByIdTypeQuery.Routine>() {
                    @Override
                    public void onChanged(GetRoutinesByIdTypeQuery.Routine routine) {
                        Uri uri = Uri.parse(routine.getLinkPreview());
                        imgRoutine.setVideoURI(uri);
                        imgRoutine.start();
                        MediaController mediaController = new MediaController(getContext());
                        mediaController.setAnchorView(imgRoutine);
                        imgRoutine.setMediaController(mediaController);
                        txtNameRoutineInformation.setText(routine.getName());
                        txtPriceRoutineInformation.setText(routine.getPrice() + "");
                        txtDescriptionRoutineInformation.setText(routine.getDescription());
                        txtRaitingRoutineInformation.setText(routine.getRaiting() + "");
                        txtNumRaitingRoutineInformation.setText(routine.getNumRaitings() + "");
                        txtTypeRoutineRoutineInformation.setText(routine.getType().getName() + "");
                    }
                }
        );
        this.btnRequestRoutine = (Button) view.findViewById(R.id.btnRequestRoutine);
        this.btnRequestRoutine.setOnClickListener(new GoToRequestRoutineListener());
        //This is a test for github

    }

    private class GoToRequestRoutineListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            navController.navigate(R.id.action_userRoutinePreviewFragment_to_userRoutineRequestFragment);
        }
    }
}
