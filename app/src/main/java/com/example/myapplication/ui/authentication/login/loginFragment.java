package com.example.myapplication.ui.authentication.login;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.AuthAuthenticationQuery;
import com.example.apollographqlandroid.AuthValidateAuthTokenQuery;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Models.AuthModel;
import com.example.myapplication.R;
import android.text.Editable;
import android.view.KeyEvent;

import com.example.myapplication.ui.AuthGlobalState;
import com.example.myapplication.ui.GlobalState;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;

/**
 * Fragment representing the login screen for Shrine.
 */
public class loginFragment extends Fragment {

    AuthGlobalState globalState;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        globalState= ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        final Bundle prop = new Bundle();


        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputLayout userTextInput = view.findViewById(R.id.User_text_input);


        final TextInputEditText userEditText = view.findViewById(R.id.User_edit_text);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);


        MaterialButton changeButton = view.findViewById((R.id.changeButton));
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        MaterialButton registerButton = view.findViewById(R.id.cancel_button);

        changeButton.setVisibility(view.GONE);

        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        SharedPreferences.Editor myEditor = myPreferences.edit();
        String Initoken = myPreferences.getString("token", "unknown");
        if(Initoken!="unknown"){
            AuthModel.authValidateAuthToken(Initoken, new ApolloCall.Callback<AuthValidateAuthTokenQuery.Data>() {
                @Override
                public void onResponse(@NotNull Response<AuthValidateAuthTokenQuery.Data> response) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override public void run() {
                            if (response.hasErrors()) {

                            } else {
                                globalState.setToken(Initoken);
                                Integer typeId = response.data().authValidateAuthToken().TypeID();
                                Boolean profile = response.data().authValidateAuthToken().Profile();
                                globalState.setTypeID(typeId);
                                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.login, true).build();
                                if(profile){
                                    navController.navigate(R.id.go_to_main,null,navOption);
                                }else{
                                    //Navegar funcion de vale
                                    navController.navigate(R.id.go_to_main,null,navOption);
                                }


                                //authValidateAuthToken

                                ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                            }
                        }
                    });
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {

                }
            });
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTextInput.setError(null);
                passwordTextInput.setError(null);
                navController.navigate(R.id.go_to_register);
            }
        });

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTextInput.setError(null);
                passwordTextInput.setError(null);
                navController.navigate(R.id.go_to_changePassword);
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();
                AuthModel.authAuthentication(email, password,new ApolloCall.Callback<AuthAuthenticationQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<AuthAuthenticationQuery.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                if (response.hasErrors()) {
                                    String error = response.errors().get(0).message().toString().substring(3);
                                    userTextInput.setError(error);
                                    passwordTextInput.setError(" ");
                                    changeButton.setVisibility(view.VISIBLE);
                                } else {
                                    String token = response.data().authAuthentication().Token();
                                    globalState.setToken(token);
                                    myEditor.putString("token", token).commit();
                                        //authValidateAuthToken
                                    AuthModel.authValidateAuthToken(token, new ApolloCall.Callback<AuthValidateAuthTokenQuery.Data>() {
                                        @Override
                                        public void onResponse(@NotNull Response<AuthValidateAuthTokenQuery.Data> response) {
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override public void run() {
                                                    if (response.hasErrors()) {

                                                    } else {
                                                        Integer typeId = response.data().authValidateAuthToken().TypeID();
                                                        Boolean profile = response.data().authValidateAuthToken().Profile();
                                                        globalState.setTypeID(typeId);
                                                        NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.login, true).build();
                                                            if(profile){
                                                                navController.navigate(R.id.go_to_main,null,navOption);
                                                            }else{
                                                                //Navegar funcion de vale
                                                               navController.navigate(R.id.go_to_main,null,navOption);
                                                            }


                                                        //authValidateAuthToken

                                                        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                                                    }
                                                }
                                            });
                                        }

                                        @Override
                                        public void onFailure(@NotNull ApolloException e) {

                                        }
                                    });

                                    ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                    }
                });

            }
        });
    }

}