package com.example.myapplication.Model.Repositories;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.AuthCreateUserMutation;
import com.example.apollographqlandroid.GetRoutinesQuery;
import com.example.myapplication.Model.Repositories.Data.Client;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AuthRepository {

    public static ApolloClient apolloClient= Client.getApolloClient();

    public AuthRepository(){

    }
    public static void authCreateUser(ApolloCall.Callback<AuthCreateUserMutation.Data>listener){
        apolloClient.mutate(AuthCreateUserMutation.builder().build()).enqueue(new ApolloCall.Callback<AuthCreateUserMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<AuthCreateUserMutation.Data> response) {
                System.out.println("los datos son "+response.data().toString());
                //String response= response.data().authCreateUser();
                //System.out.println("description is"+array.get(0).description());

                //listener.onSuccess(array);
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println("el error es "+e.getMessage());


            }
        });

    }

}
