package com.example.myapplication.ui.Profile;

import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.EditProfileTrainerMutation;
import com.example.apollographqlandroid.ProfileTrainerQuery;
import com.example.apollographqlandroid.CreateProfileMutation;
import com.example.myapplication.Model.Repositories.ProfileTrainerRepository;
import com.example.myapplication.R;
import com.example.myapplication.ui.AuthGlobalState;
import com.example.myapplication.ui.GlobalState;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListSpecialitiesTrainerFragment extends Fragment {

    AuthGlobalState authGlobalState;
    Context context;

    public static ListSpecialitiesTrainerFragment newInstance() {
        return new ListSpecialitiesTrainerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view=inflater.inflate(R.layout.fragment_list_specialities_trainer, container, false);
        authGlobalState = ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listSpecialities = view.findViewById(R.id.specialitiesList);


        MaterialButton buttonAddSpecialitiesTrainer = view.findViewById(R.id.buttonAddSpecialitiesTrainer);
        final NavController navController= Navigation.findNavController(view);

        ProfileTrainerRepository.getProfileTrainer(new ApolloCall.Callback<ProfileTrainerQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileTrainerQuery.Data> response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {
                        if(response.data().profileTrainer()!=null) {
                            List<String> spe = response.data().profileTrainer().specialities();
                            System.out.println(spe);
                            ArrayList<String> specialities = new ArrayList<>(spe);
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, specialities);
                            listSpecialities.setAdapter(adapter);
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println();
            }
        },authGlobalState.getToken().getValue());



        buttonAddSpecialitiesTrainer .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.addSpecialitiesTrainer, true).build();
                navController.navigate(R.id.go_to_add_specialities,null,navOption);
            }
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
