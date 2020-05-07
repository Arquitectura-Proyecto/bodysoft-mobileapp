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
public class UserEditFragment extends Fragment {

    AuthGlobalState authGlobalState;

    public static UserEditFragment newInstance() {
        return new UserEditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_edit, container, false);
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

        TextView user_name = view.findViewById(R.id.nameUserEditProfile);
        TextInputEditText telephone = view.findViewById(R.id.editTelephone);
        TextInputEditText city = view.findViewById(R.id.editCity);
        TextInputEditText age = view.findViewById(R.id.editAge);

        MaterialButton buttonSaveEditUser = view.findViewById(R.id.buttonSaveEditUser);
        final NavController navController= Navigation.findNavController(view);



        ProfileRepository.getProfileUser(new ApolloCall.Callback<ProfileUserQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileUserQuery.Data> response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {
                        if(response.data().profileUser()!=null) {
                            user_name.setText(response.data().profileUser().user_name());
                            age.setText(response.data().profileUser().age().toString());
                            telephone.setText(response.data().profileUser().telephone());
                            city.setText(response.data().profileUser().city());
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println(e);
            }
        },authGlobalState.getToken().getValue());





        buttonSaveEditUser .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fieldValidate(age)  | !fieldValidate(telephone) | !fieldValidate(city)){ return; }

                ProfileRepository.editProfileUser(new ApolloCall.Callback<EditProfileUserMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<EditProfileUserMutation.Data> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override public void run() {
                                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.fragment_profile, true).build();
                                navController.navigate(R.id.go_to_user_profile,null,navOption);
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        System.out.println(e);
                    }
                },user_name.getText().toString(),Integer.parseInt(age.getText().toString()),"aquifoto",telephone.getText().toString(),city.getText().toString(),authGlobalState.getToken().getValue());



            }
        });




    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
