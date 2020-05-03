package com.example.myapplication.ui.Profile;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.ProfileUserQuery;
import com.example.apollographqlandroid.ProfileTrainerQuery;
import com.example.myapplication.Model.Repositories.ProfileRepository;
import com.example.myapplication.Model.Repositories.ProfileTrainerRepository;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

import java.util.StringTokenizer;

public class TrainerFragment extends Fragment {

    GlobalState globalState;

    public static TrainerFragment newInstance() {
        return new TrainerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_trainer, container, false);
        globalState= ViewModelProviders.of(getActivity()).get(GlobalState.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView user_name = view.findViewById(R.id.nameTrainerProfile);
        TextView telephone = view.findViewById(R.id.telephoneTrainerProfile);
        TextView city = view.findViewById(R.id.cityTrainerProfile);
        TextView age = view.findViewById(R.id.ageTrainerProfile);
        TextView degree = view.findViewById(R.id.degreeTrainerProfile);
        TextView description = view.findViewById(R.id.descriptionTrainerProfile);
        TextView experience = view.findViewById(R.id.experienceTrainerProfile);
        TextView resources = view.findViewById(R.id.resourcesTrainerProfile);

        MaterialButton buttonEditTrainer = view.findViewById(R.id.buttonEditTrainer);
        MaterialButton buttonSpecialitiesTrainer = view.findViewById(R.id.buttonSpecialitiesTrainer);
        MaterialButton buttonDegreesTrainer = view.findViewById(R.id.buttonDegreesTrainer);
        final NavController navController= Navigation.findNavController(view);


        ProfileTrainerRepository.getProfileTrainer(new ApolloCall.Callback<ProfileTrainerQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileTrainerQuery.Data> response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {

                        user_name.setText(response.data().profileTrainer().trainer_name());
                        age.setText(response.data().profileTrainer().age().toString());
                        telephone.setText(response.data().profileTrainer().telephone());
                        city.setText(response.data().profileTrainer().city());
                        description.setText(response.data().profileTrainer().description());
                        experience.setText(response.data().profileTrainer().work_experience());
                        resources.setText(response.data().profileTrainer().resources());
                        int div = response.data().profileTrainer().num_ratings();
                        if (div!=0) {
                            float calc = (float)response.data().profileTrainer().sum_ratings() / div;
                            String val = String.format("%.1f",calc);
                            degree.setText(val);
                        }else {
                            degree.setText("0");
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println();
            }
        },"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJRCI6MiwiUHJvZmlsZSI6ZmFsc2UsIlR5cGVJRCI6MSwiZXhwIjoxNTg4NDY2MzE5fQ.F96YzanVY2G6HJV_Y3jjuUpazFmigMPTuz2IbHe5hoE");


        buttonEditTrainer .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.trainerEditFragment, true).build();
                navController.navigate(R.id.go_to_edit_trainer,null,navOption);
            }
        });

        buttonSpecialitiesTrainer .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.listSpecialitiesTrainerFragment, true).build();
                navController.navigate(R.id.go_to_list_specialities,null,navOption);
            }
        });

        buttonDegreesTrainer .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.listDegreesTrainerFragment, true).build();
                navController.navigate(R.id.go_to_list_degrees,null,navOption);
            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
