package com.example.myapplication.Model.Repositories;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.GetRoutinesQuery;
import com.example.myapplication.Model.Models.ModelListener;
import com.example.myapplication.Model.Repositories.Data.Client;
import com.example.myapplication.ui.GlobalState;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RoutineRepository {
    public static ApolloClient apolloClient= Client.getApolloClient();

    public RoutineRepository(){

    }
    public static void getAllRoutines(ApolloCall.Callback<GetRoutinesQuery.Data>listener){
        apolloClient.query(GetRoutinesQuery.builder().build()).enqueue(new ApolloCall.Callback<GetRoutinesQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetRoutinesQuery.Data> response) {
                System.out.println("los datos son "+response.data().toString());
                List<GetRoutinesQuery.GetAllRoutine> array= response.data().getAllRoutines();
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
