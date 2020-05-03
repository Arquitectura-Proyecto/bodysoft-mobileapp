package com.example.myapplication.ui.authentication.recoverPassword;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.AuthRecoverPasswordQuery;
import com.example.myapplication.Model.Models.AuthModel;
import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

public class recoverPasswordFragment extends Fragment {
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recover_password, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        final Bundle prop = new Bundle();

        LinearLayout firstView = view.findViewById(R.id.FirstView);
        LinearLayout secondView = view.findViewById(R.id.SecondView);

        secondView.setVisibility(view.GONE);

        MaterialButton recuperaButton = view.findViewById(R.id.changeButton);
        MaterialButton AceptarButton = view.findViewById(R.id.backLogin);

        final TextInputLayout emailTextInput = view.findViewById(R.id.User_text_input);

        final TextInputEditText emailEditText = view.findViewById(R.id.User_edit_text);


        recuperaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailEditText.getText().toString();

                AuthModel.authRecoverPassword(email, new ApolloCall.Callback<AuthRecoverPasswordQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<AuthRecoverPasswordQuery.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.hasErrors()) {
                                    String error = response.errors().get(0).message().toString().substring(3);
                                    emailTextInput.setError(error);
                                } else {
                                    firstView.setVisibility(view.GONE);
                                    secondView.setVisibility(view.VISIBLE);

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

        //

        AceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.recoverPasswordFragment, true).build();
                navController.navigate(R.id.go_to_login,null,navOption);
            }
        });


    }

}
