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
import com.example.myapplication.Model.Repositories.ProfileRepository;
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
public class CreateUserFragment extends Fragment {

    AuthGlobalState authGlobalState;

    public static CreateUserFragment newInstance() {
        return new CreateUserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_create_user, container, false);
        authGlobalState = ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);
        return view;
    }

    private boolean fieldValidate (TextInputEditText text){
        text.setError(null);
        if("".equals(text.getText().toString())) {
            text.setError("Campo necesario");
            text.requestFocus();
            return false;
        }
        return true;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText user_name = view.findViewById(R.id.createNameUser);
        TextInputEditText telephone = view.findViewById(R.id.createTelephoneUser);
        TextInputEditText city = view.findViewById(R.id.createCityUser);
        TextInputEditText age = view.findViewById(R.id.createAgeUser);

        MaterialButton buttonCreateUser = view.findViewById(R.id.buttonCreateUser);
        final NavController navController= Navigation.findNavController(view);



        buttonCreateUser .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!fieldValidate(user_name) | !fieldValidate(telephone) | !fieldValidate(city) | !fieldValidate(age) ){ return; }


                ProfileRepository.createProfileUser(new ApolloCall.Callback<CreateProfileMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<CreateProfileMutation.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.nav_home, true).build();
                                navController.navigate(R.id.from_create_user_to_home,null,navOption);
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        System.out.println(e);
                    }
                },user_name.getText().toString(),Integer.parseInt(age.getText().toString()),"none",telephone.getText().toString(),city.getText().toString(),authGlobalState.getToken().getValue());



            }
        });




    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}

