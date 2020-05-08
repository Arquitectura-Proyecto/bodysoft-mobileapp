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
import com.example.apollographqlandroid.ProfileSpecialitiesToAddQuery;
import com.example.apollographqlandroid.ProfileDegreesQuery;
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
public class ListDegreesTrainerFragment extends Fragment {

    AuthGlobalState authGlobalState;
    Context context;


    public static ListDegreesTrainerFragment newInstance() {
        return new ListDegreesTrainerFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view=inflater.inflate(R.layout.fragment_list_degrees_trainer, container, false);
        authGlobalState = ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listSpecialities = view.findViewById(R.id.degreesList);
        AdapterDegrees adapter;


        MaterialButton buttonAddDegreesTrainer = view.findViewById(R.id.buttonAddDegreesTrainer);
        final NavController navController= Navigation.findNavController(view);

        ArrayList<EntityDegree> degrees = new ArrayList<>();
        final EntityDegree[] d = new EntityDegree[1];

        ProfileTrainerRepository.getProfileDegrees(new ApolloCall.Callback<ProfileDegreesQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileDegreesQuery.Data> response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {

                        if(response.data().profileDegreesByTrainers()!=null){
                            List<ProfileDegreesQuery.ProfileDegreesByTrainer> array= response.data().profileDegreesByTrainers();
                            for ( ProfileDegreesQuery.ProfileDegreesByTrainer item: array) {
                                EntityDegree d = new EntityDegree(item.degree_name(),String.valueOf(item.year()),item.institution(), R.drawable.degrees_image);
                                degrees.add(d);
                            }
                            AdapterDegrees adapter = new AdapterDegrees(context, degrees);
                            listSpecialities.setAdapter(adapter);

                        }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println(e);
            }
        },authGlobalState.getToken().getValue());



        buttonAddDegreesTrainer .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.addDegreesTrainerFragment, true).build();
                navController.navigate(R.id.go_to_add_degrees,null,navOption);
            }
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
