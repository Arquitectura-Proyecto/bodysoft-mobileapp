package com.example.myapplication.Model.Repositories;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.GetCurrentbyIdQuery;
import com.example.myapplication.Model.Repositories.Data.Client;

import org.jetbrains.annotations.NotNull;

public class SessionRepository {

    public static ApolloClient apolloClient= Client.getApolloClient();

    public SessionRepository(){

    }

    public static void getCurrentbyId(String token, ApolloCall.Callback<GetCurrentbyIdQuery.Data>listener){
        apolloClient.query(GetCurrentbyIdQuery
                .builder().token(token)
                .build()).enqueue(new ApolloCall.Callback<GetCurrentbyIdQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetCurrentbyIdQuery.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {


            }
        });

    }
}
