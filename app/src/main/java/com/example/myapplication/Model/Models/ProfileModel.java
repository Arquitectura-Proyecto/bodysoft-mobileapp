package com.example.myapplication.Model.Models;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.ProfileUserQuery;
import com.example.apollographqlandroid.ProfileUsersQuery;
import com.example.myapplication.Model.Repositories.Data.Client;
import com.example.myapplication.Model.Repositories.ProfileRepository;


import org.jetbrains.annotations.NotNull;

public class ProfileModel {
    private static ProfileRepository profileRepository;
    public static ApolloClient apolloClient= Client.getApolloClient();
    public ProfileModel(){}

    public static  void getProfileUsers(ApolloCall.Callback<ProfileUsersQuery.Data>listener){

        ProfileRepository.getProfileUsers(new ApolloCall.Callback<ProfileUsersQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProfileUsersQuery.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }



    public static  void getProfileUser(ApolloCall.Callback<ProfileUserQuery.Data>listener,String token){

        apolloClient.query(ProfileUserQuery.builder().token(token).build()).enqueue(
                new ApolloCall.Callback<ProfileUserQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<ProfileUserQuery.Data> response) {
                        System.out.println("agregado:"+response.data());
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        System.out.println( e);
                    }
                }
        );
    }
}
