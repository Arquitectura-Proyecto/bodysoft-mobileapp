package com.example.myapplication.ui.Profile;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.EditProfileTrainerMutation;
import com.example.apollographqlandroid.ProfileTrainerQuery;
import com.example.apollographqlandroid.CreateProfileMutation;
import com.example.apollographqlandroid.ProfileSpecialitiesToAddQuery;
import com.example.apollographqlandroid.CreateProfileTrainerSpecialityMutation;
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
public class AddSpecialitiesTrainer extends Fragment {

    AuthGlobalState authglobalState;
    Context context;

    public static AddSpecialitiesTrainer newInstance() {
        return new AddSpecialitiesTrainer();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view=inflater.inflate(R.layout.fragment_add_specialities_trainer, container, false);
        authglobalState = ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listSpecialities = view.findViewById(R.id.specialitiesAddList);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);


        final NavController navController= Navigation.findNavController(view);
        ArrayList<String> idSpec = new ArrayList<>();
        ArrayList<String> specialities = new ArrayList<>();

        ProfileTrainerRepository.getSpecialitiesToAdd(new ApolloCall.Callback<ProfileSpecialitiesToAddQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileSpecialitiesToAddQuery.Data> response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {

                        if(response.data().profileToAddSpecialitities()!=null){
                            List<ProfileSpecialitiesToAddQuery.ProfileToAddSpecialitity> array= response.data().profileToAddSpecialitities();
                            for (ProfileSpecialitiesToAddQuery.ProfileToAddSpecialitity item: array) {
                                specialities.add(item.speciality_name() );
                                idSpec.add(item.speciality_id());
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, specialities);
                            listSpecialities.setAdapter(adapter);
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println(e);
            }
        },authglobalState.getToken().getValue());


        listSpecialities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String newSpeciality =  specialities.get(position);

                builder.setTitle("Agregar especialidad");
                builder.setMessage("¿Deseas agregar " + newSpeciality + " a tus especialidades?");

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ProfileTrainerRepository.createProfileTrainerSpeciality(new ApolloCall.Callback<CreateProfileTrainerSpecialityMutation.Data>() {
                            @Override
                            public void onResponse(@NotNull Response<CreateProfileTrainerSpecialityMutation.Data> response) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override public void run() {
                                        if (response.data().createProfileTrainerSpeciality() != null) {

                                            String message =  "Especialidad " + specialities.get(position) + " agregada";
                                            Toast toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
                                            toast.show();
                                            System.out.println(response.data().createProfileTrainerSpeciality().speciality());
                                            idSpec.remove(position);
                                            specialities.remove(position);

                                            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, specialities);
                                            listSpecialities.setAdapter(adapter);
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onFailure(@NotNull ApolloException e) {
                                System.out.println(e);;
                            }
                        },idSpec.get(position),authglobalState.getToken().getValue());

                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
