package com.example.myapplication.ui.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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
import com.example.apollographqlandroid.EditProfileTrainerMutation;
import com.example.apollographqlandroid.ProfileTrainerQuery;
import com.example.myapplication.Model.Repositories.ProfileTrainerRepository;
import com.example.myapplication.R;
import com.example.myapplication.ui.AuthGlobalState;
import com.example.myapplication.ui.GlobalState;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainerEditFragment extends Fragment {

    AuthGlobalState authGlobalState;

    public static TrainerEditFragment newInstance() {
        return new TrainerEditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_trainer_edit, container, false);
        authGlobalState = ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView trainer_name = view.findViewById(R.id.nameTrainerEditProfile);
        TextInputEditText telephone = view.findViewById(R.id.editTelephoneTrainer);
        TextInputEditText city = view.findViewById(R.id.editCityTrainer);
        TextInputEditText age = view.findViewById(R.id.editAgeTrainer);
        TextInputEditText degree = view.findViewById(R.id.editGradeTrainer);
        TextInputEditText description = view.findViewById(R.id.editDescriptionTrainer);
        TextInputEditText experience = view.findViewById(R.id.editExperienceTrainer);
        TextInputEditText resources = view.findViewById(R.id.editResourcesTrainer);

        MaterialButton buttonSaveEditTrainer = view.findViewById(R.id.buttonSaveEditTrainer);
        final NavController navController= Navigation.findNavController(view);

        int cal [] = {0,0};

        ProfileTrainerRepository.getProfileTrainer(new ApolloCall.Callback<ProfileTrainerQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileTrainerQuery.Data> response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {
                        if(response.data().profileTrainer()!=null) {
                            trainer_name.setText(response.data().profileTrainer().trainer_name());
                            age.setText(response.data().profileTrainer().age().toString());
                            telephone.setText(response.data().profileTrainer().telephone());
                            city.setText(response.data().profileTrainer().city());
                            description.setText(response.data().profileTrainer().description());
                            experience.setText(response.data().profileTrainer().work_experience());
                            resources.setText(response.data().profileTrainer().resources());
                            cal[0] = response.data().profileTrainer().num_ratings();
                            if (cal[0] != 0) {
                                cal[1] = response.data().profileTrainer().sum_ratings();
                                float calc = (float) cal[1] / cal[0];
                                String val = String.format("%.1f", calc);
                                degree.setText(val);
                            } else {
                                degree.setText("0");
                            }
                        }

                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println(e);
            }
        },authGlobalState.getToken().getValue());

        buttonSaveEditTrainer .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProfileTrainerRepository.editProfileTrainer(new ApolloCall.Callback<EditProfileTrainerMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<EditProfileTrainerMutation.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.trainerFragment, true).build();
                                navController.navigate(R.id.go_to_trainer_profile,null,navOption);
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        System.out.println(e);
                    }
                },trainer_name.getText().toString(),Integer.parseInt(age.getText().toString()),"aquifoto",telephone.getText().toString(),city.getText().toString(),cal[1],cal[0],description.getText().toString(),resources.getText().toString(),experience.getText().toString(),authGlobalState.getToken().getValue());

            }
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
