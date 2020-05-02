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
import com.example.apollographqlandroid.CreateProfileMutation;
import com.example.myapplication.Model.Repositories.ProfileTrainerRepository;
import com.example.myapplication.R;
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
public class CreateTrainerFragment extends Fragment {

    GlobalState globalState;

    public static CreateTrainerFragment newInstance() {
        return new CreateTrainerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_create_trainer, container, false);
        globalState = ViewModelProviders.of(getActivity()).get(GlobalState.class);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText trainer_name = view.findViewById(R.id.createNameTrainer);
        TextInputEditText telephone = view.findViewById(R.id.createTelephoneTrainer);
        TextInputEditText city = view.findViewById(R.id.createCityTrainer);
        TextInputEditText age = view.findViewById(R.id.createAgeTrainer);
        TextInputEditText description = view.findViewById(R.id.createDescriptionTrainer);
        TextInputEditText experience = view.findViewById(R.id.createExperienceTrainer);
        TextInputEditText resources = view.findViewById(R.id.createResourcesTrainer);

        MaterialButton buttonCreateTrainer = view.findViewById(R.id.buttonCreateTrainer);
        final NavController navController= Navigation.findNavController(view);

        int cal [] = {0,0};

        buttonCreateTrainer .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProfileTrainerRepository.createProfileTrainer(new ApolloCall.Callback<CreateProfileMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<CreateProfileMutation.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.nav_home, true).build();
                                navController.navigate(R.id.from_create_trainer_to_home,null,navOption);
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        System.out.println(e);
                    }
                },trainer_name.getText().toString(),Integer.parseInt(age.getText().toString()),"aquifoto",telephone.getText().toString(),city.getText().toString(),description.getText().toString(),resources.getText().toString(),experience.getText().toString(),"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJRCI6MiwiUHJvZmlsZSI6ZmFsc2UsIlR5cGVJRCI6MSwiZXhwIjoxNTg4NDY2MzE5fQ.F96YzanVY2G6HJV_Y3jjuUpazFmigMPTuz2IbHe5hoE");

            }
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}