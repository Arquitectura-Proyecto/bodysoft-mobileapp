package com.example.myapplication.ui.Profile;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgs;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.bumptech.glide.Glide;
import com.example.apollographqlandroid.ProfileUserQuery;
import com.example.myapplication.Model.Models.ProfileModel;
import com.example.myapplication.Model.Repositories.ProfileRepository;
import com.example.myapplication.R;
import com.example.myapplication.ui.AuthGlobalState;
import com.example.myapplication.ui.GlobalState;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

public class UserProfileFragment extends Fragment {

    Context context;
    String ruta;
    FirebaseStorage storage;
    StorageReference storageRef;
    StorageReference spaceRef;
    AuthGlobalState authGlobalState;

    public static UserProfileFragment newInstance() {
        return new UserProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_profile, container, false);
        context = getContext();
        authGlobalState= ViewModelProviders.of(getActivity()).get(AuthGlobalState.class);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        ruta = "gs://bodysoft-test.appspot.com";
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView user_name = view.findViewById(R.id.nameUserProfile);
        TextView telephone = view.findViewById(R.id.telephoneUserProfile);
        TextView city = view.findViewById(R.id.cityUser);
        TextView age = view.findViewById(R.id.ageUserProfile);
        ImageView photo = view.findViewById(R.id.userPhoto);


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


                            String ph = response.data().profileUser().photo();
                            if ("none".equals(ph)){
                                photo.setImageResource(R.drawable.user_dos);
                            }else {
                                Glide.with(context)
                                        .load(ph)
                                        .into(photo);
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println(e);
            }
        },authGlobalState.getToken().getValue());


        MaterialButton buttonEditUser = view.findViewById(R.id.buttonEditUser);
        final NavController navController= Navigation.findNavController(view);
        buttonEditUser .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.userEditFragment, true).build();
                navController.navigate(R.id.go_to_edit_user,null,navOption);
            }
        });



        MaterialButton buttonPasswordUser = view.findViewById(R.id.buttonPasswordUser);
        buttonPasswordUser .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOption = new NavOptions.Builder().setPopUpTo(R.id.changePasswordFragment, true).build();
                navController.navigate(R.id.from_user_to_change_password,null,navOption);
            }
        });






    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}

