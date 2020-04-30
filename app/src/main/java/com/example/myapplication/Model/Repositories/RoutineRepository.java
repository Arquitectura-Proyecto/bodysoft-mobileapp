package com.example.myapplication.Model.Repositories;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.CrearRutinaMutation;
import com.example.apollographqlandroid.GetAllTypeRoutineQuery;
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.apollographqlandroid.GetRoutinesQuery;
import com.example.myapplication.Model.Entities.RoutineEntity;
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
    public static void getRoutinesByIdOwner(ApolloCall.Callback<GetRoutinesByIdOwnerQuery.Data>listener,String token){
    apolloClient.query(GetRoutinesByIdOwnerQuery.builder().token(token).build()).enqueue(new RoutineGraphQLListener<GetRoutinesByIdOwnerQuery.Data>(listener));
    }
    public static void getAllTypeRoutine(ApolloCall.Callback<GetAllTypeRoutineQuery.Data>listener){
        apolloClient.query(GetAllTypeRoutineQuery.builder().build()).enqueue(new GetAllTypeRoutineListener<GetAllTypeRoutineQuery.Data>(listener));
    }
    public static void registerRoutine(ApolloCall.Callback<CrearRutinaMutation.Data>listener, RoutineEntity newroutineEntity,String token){
        apolloClient.mutate(
                CrearRutinaMutation.builder()
                        .price(newroutineEntity.getPrice())
                        .name(newroutineEntity.getName())
                        .description(newroutineEntity.getDescription())
                        .link_preview(newroutineEntity.getLink_preview())
                        .token(token)
                        .idType(newroutineEntity.getIdtype())
                        .build()).enqueue(new CreateRoutineListener<CrearRutinaMutation.Data>(listener));

    }



private static class RoutineGraphQLListener <T> extends ApolloCall.Callback<T> {
        ApolloCall.Callback<T> callback;
        public RoutineGraphQLListener(ApolloCall.Callback<T>callback){
            this.callback=callback;
        }


    @Override
    public void onResponse(@NotNull Response<T> response) {
        callback.onResponse(response);
    }

    @Override
    public void onFailure(@NotNull ApolloException e) {
    callback.onFailure(e);
    }
}
private static class GetAllTypeRoutineListener<T> extends ApolloCall.Callback<T>{
    ApolloCall.Callback<T>callback;
        public GetAllTypeRoutineListener(ApolloCall.Callback<T>callback){
            this.callback=callback;
    }
    @Override
    public void onResponse(@NotNull Response<T> response) {
        callback.onResponse(response);
    }

    @Override
    public void onFailure(@NotNull ApolloException e) {
        callback.onFailure(e);
    }
}
private static class CreateRoutineListener<T> extends ApolloCall.Callback<T>{
    ApolloCall.Callback<T>callback;

    public CreateRoutineListener(ApolloCall.Callback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(@NotNull Response<T> response) {
        this.callback.onResponse(response);
    }

    @Override
    public void onFailure(@NotNull ApolloException e) {
    this.callback.onFailure(e);
    }
}

}
