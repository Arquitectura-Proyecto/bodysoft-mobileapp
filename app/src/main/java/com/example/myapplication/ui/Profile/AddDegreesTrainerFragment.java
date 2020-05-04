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
import com.example.apollographqlandroid.ProfileUserQuery;
import com.example.apollographqlandroid.EditProfileUserMutation;
import com.example.apollographqlandroid.CreateProfileMutation;
import com.example.apollographqlandroid.CreateDegreeMutation;
import com.example.myapplication.Model.Repositories.ProfileRepository;
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
public class AddDegreesTrainerFragment extends Fragment {

    AuthGlobalState authGlobalState;

    public static AddDegreesTrainerFragment newInstance() {
        return new AddDegreesTrainerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_degrees_trainer, container, false);
        authGlobalState = ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText degree_name = view.findViewById(R.id.createNameDegree);
        TextInputEditText institution = view.findViewById(R.id.createInstitutionDegree);
        TextInputEditText year = view.findViewById(R.id.createYearDegree);

        MaterialButton buttonCreateDegree = view.findViewById(R.id.buttonCreateDegree);
        final NavController navController= Navigation.findNavController(view);



        buttonCreateDegree .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProfileTrainerRepository.createProfileTrainerDegree(new ApolloCall.Callback<CreateDegreeMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<CreateDegreeMutation.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.listDegreesTrainerFragment, true).build();
                                navController.navigate(R.id.from_add_to_list_degrees,null,navOption);
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        System.out.println(e);
                    }
                },degree_name.getText().toString(),Integer.parseInt(year.getText().toString()),institution.getText().toString(),authGlobalState.getToken().getValue());



            }
        });




    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}

