package com.example.myapplication.Model.Models;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.GetCurrentbyIdQuery;
import com.example.myapplication.Model.Repositories.SessionRepository;

import org.jetbrains.annotations.NotNull;

public class SessionModel {

    private static SessionRepository sessionRepository;

    public static void getCurrentbyId(String token, ApolloCall.Callback<GetCurrentbyIdQuery.Data>listener) {

        sessionRepository.getCurrentbyId(token, new ApolloCall.Callback<GetCurrentbyIdQuery.Data>() {
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
