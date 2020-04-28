package com.example.myapplication.Model.Repositories;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.AuthAuthenticationQuery;
import com.example.apollographqlandroid.AuthCreateUserMutation;
import com.example.apollographqlandroid.AuthValidateAuthTokenQuery;
import com.example.apollographqlandroid.AuthVerifyAcountMutation;
import com.example.apollographqlandroid.GetRoutinesQuery;
import com.example.myapplication.Model.Repositories.Data.Client;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AuthRepository {

    public static ApolloClient apolloClient= Client.getApolloClient();

    public AuthRepository(){

    }
    public static void authCreateUser(String email, String password, int typeId, ApolloCall.Callback<AuthCreateUserMutation.Data>listener){
        apolloClient.mutate(AuthCreateUserMutation
                .builder().email(email).password(password).typeId(typeId)
                .build()).enqueue(new ApolloCall.Callback<AuthCreateUserMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<AuthCreateUserMutation.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });

    }

    public static void authVerifyAcount(String email, int vcode, ApolloCall.Callback<AuthVerifyAcountMutation.Data>listener){
        apolloClient.mutate(AuthVerifyAcountMutation
                .builder().email(email).vcode(vcode)
                .build()).enqueue(new ApolloCall.Callback<AuthVerifyAcountMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<AuthVerifyAcountMutation.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });

    }


    public static void authAuthentication(String email, String password, ApolloCall.Callback<AuthAuthenticationQuery.Data>listener){
        apolloClient.query(AuthAuthenticationQuery
                .builder().email(email).password(password)
                .build()).enqueue(new ApolloCall.Callback<AuthAuthenticationQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<AuthAuthenticationQuery.Data> response) {

                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {


            }
        });

    }

    public static void authValidateAuthToken(String token, ApolloCall.Callback<AuthValidateAuthTokenQuery.Data>listener){
        apolloClient.query(AuthValidateAuthTokenQuery
                .builder().token(token)
                .build()).enqueue(new ApolloCall.Callback<AuthValidateAuthTokenQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<AuthValidateAuthTokenQuery.Data> response) {

                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {


            }
        });

    }
}
