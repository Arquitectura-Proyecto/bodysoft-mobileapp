package com.example.myapplication.Model.Repositories;

import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.ProfileUsersQuery;
import com.example.apollographqlandroid.ProfileUserQuery;
import com.example.apollographqlandroid.EditProfileUserMutation;
import com.example.apollographqlandroid.CreateProfileMutation;
import com.example.myapplication.Model.Models.ModelListener;
import com.example.myapplication.Model.Repositories.Data.Client;
import com.example.myapplication.ui.GlobalState;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProfileRepository {
    public static ApolloClient apolloClient= Client.getApolloClient();

    public ProfileRepository(){

    }
    public static void getProfileUsers(ApolloCall.Callback<ProfileUsersQuery.Data>listener){
        apolloClient.query(ProfileUsersQuery.builder().build()).enqueue(new ApolloCall.Callback<ProfileUsersQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileUsersQuery.Data> response) {
                System.out.println("los datos son "+response.data().toString());
                List<ProfileUsersQuery.ProfileUser> array= response.data().profileUsers();
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                listener.onFailure(e);

            }
        });
    }

    public static void getProfileUser(ApolloCall.Callback<ProfileUserQuery.Data>listener, String token){
        apolloClient.query(ProfileUserQuery.builder().token(token).build()).enqueue(new ApolloCall.Callback<ProfileUserQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileUserQuery.Data> response) {
                ProfileUserQuery.ProfileUser user= response.data().profileUser();
                System.out.println("repo" + user);
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                listener.onFailure(e);
            }
        });
    }

    public static void editProfileUser(ApolloCall.Callback<EditProfileUserMutation.Data>listener, String user_name, int age, String photo, String telephone, String city, String token){
        apolloClient.mutate(EditProfileUserMutation.builder().user_name(user_name).age(age).photo(photo).telephone(telephone).city(city).token(token).build()).enqueue(new ApolloCall.Callback<EditProfileUserMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<EditProfileUserMutation.Data> response) {
                EditProfileUserMutation.UpdateProfileUser user= response.data().updateProfileUser();
                System.out.println("repo" + user);
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                listener.onFailure(e);
            }
        });
    }


    public static void createProfileUser(ApolloCall.Callback<CreateProfileMutation.Data>listener, String user_name, int age, String photo, String telephone, String city, String token){
        apolloClient.mutate(CreateProfileMutation.builder().name(user_name).age(age).photo(photo).telephone(telephone).city(city).token(token).build()).enqueue(new ApolloCall.Callback<CreateProfileMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<CreateProfileMutation.Data> response) {
                String user= response.data().createProfile();
                System.out.println("repo" + user);
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                listener.onFailure(e);
            }
        });
    }









}
