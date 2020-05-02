package com.example.myapplication.ui.authentication.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.AuthAuthenticationQuery;
import com.example.myapplication.Model.Models.AuthModel;
import com.example.myapplication.R;
import android.text.Editable;
import android.view.KeyEvent;

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

    GlobalState globalState;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        globalState= ViewModelProviders.of(getActivity()).get(GlobalState.class);

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


        MaterialButton nextButton = view.findViewById(R.id.next_button);
        MaterialButton registerButton = view.findViewById(R.id.cancel_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.go_to_register);
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
                                } else {
                                    String token = response.data().authAuthentication().Token();
                                    globalState.setString(token);
                                    NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.login, true).build();
                                    navController.navigate(R.id.go_to_main,null,navOption);

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