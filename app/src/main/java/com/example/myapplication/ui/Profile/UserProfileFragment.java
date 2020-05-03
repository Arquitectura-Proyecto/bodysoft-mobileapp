package com.example.myapplication.ui.Profile;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.myapplication.Model.Models.ProfileModel;
import com.example.myapplication.Model.Repositories.ProfileRepository;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

public class UserProfileFragment extends Fragment {

    GlobalState globalState;

    public static UserProfileFragment newInstance() {
        return new UserProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_profile, container, false);
        globalState= ViewModelProviders.of(getActivity()).get(GlobalState.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView user_name = view.findViewById(R.id.nameUserProfile);
        TextView telephone = view.findViewById(R.id.telephoneUserProfile);
        TextView city = view.findViewById(R.id.cityUser);
        TextView age = view.findViewById(R.id.ageUserProfile);

        MaterialButton buttonEditUser = view.findViewById(R.id.buttonEditUser);
        final NavController navController= Navigation.findNavController(view);
        buttonEditUser .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.userEditFragment, true).build();
                navController.navigate(R.id.go_to_edit_user,null,navOption);
            }
        });


        ProfileRepository.getProfileUser(new ApolloCall.Callback<ProfileUserQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileUserQuery.Data> response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {

                            user_name.setText(response.data().profileUser().user_name());
                            age.setText(response.data().profileUser().age().toString());
                            telephone.setText(response.data().profileUser().telephone());
                            city.setText(response.data().profileUser().city());
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println();
            }
        },"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJRCI6MywiUHJvZmlsZSI6dHJ1ZSwiVHlwZUlEIjoyLCJleHAiOjE1ODg0NTkwMTl9.D--uz_85OIEDzmfgIlnrMTRA6fZ88qciwn70dVeZYsE");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}

