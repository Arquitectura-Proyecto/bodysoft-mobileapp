package com.example.myapplication.Model.Models;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.AuthAuthenticationQuery;
import com.example.apollographqlandroid.AuthCreateUserMutation;
import com.example.apollographqlandroid.AuthVerifyAcountMutation;
import com.example.apollographqlandroid.GetRoutinesQuery;
import com.example.myapplication.Model.Repositories.AuthRepository;
import com.example.myapplication.Model.Repositories.RoutineRepository;
import com.example.myapplication.ui.AuthGlobalState;
import com.example.myapplication.ui.GlobalState;

import org.jetbrains.annotations.NotNull;

public class AuthModel {
    private static AuthRepository authRepository;
    public AuthModel(){}


    AuthGlobalState globalState;

    public static  void authCreateUser(String email, String password, int typeId, ApolloCall.Callback<AuthCreateUserMutation.Data>listener){

        authRepository.authCreateUser(email,password,typeId,new ApolloCall.Callback<AuthCreateUserMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<AuthCreateUserMutation.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    public static  void authVerifyAcount(String email,int vcode, ApolloCall.Callback<AuthVerifyAcountMutation.Data>listener){

        authRepository.authVerifyAcount(email,vcode,new ApolloCall.Callback<AuthVerifyAcountMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<AuthVerifyAcountMutation.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    public static void authAuthentication(String email, String password,ApolloCall.Callback<AuthAuthenticationQuery.Data>listener){

        authRepository.authAuthentication(email,password,new ApolloCall.Callback<AuthAuthenticationQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<AuthAuthenticationQuery.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
