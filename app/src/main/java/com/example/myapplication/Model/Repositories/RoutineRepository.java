package com.example.myapplication.Model.Repositories;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.CrearRutinaMutation;
import com.example.apollographqlandroid.DeleteRequestMutation;
import com.example.apollographqlandroid.GetAllTypeRoutineQuery;
import com.example.apollographqlandroid.GetRequestByIdRoutineQuery;
import com.example.apollographqlandroid.GetResourcesByIdRoutineMutation;
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.apollographqlandroid.GetRoutinesByIdTypeQuery;
import com.example.apollographqlandroid.GetRoutinesQuery;

import com.example.apollographqlandroid.GetUserRoutineByIdUserQuery;
import com.example.apollographqlandroid.RegisterRequestMutation;

import com.example.apollographqlandroid.RegisterResourceMutation;
import com.example.apollographqlandroid.RegisterUserRoutineMutation;

import com.example.apollographqlandroid.UpdateRoutineMutation;
import com.example.myapplication.Model.Entities.ResourceEntity;
import com.example.myapplication.Model.Entities.RoutineEntity;
import com.example.myapplication.Model.Entities.UserRoutineEntity;
import com.example.myapplication.Model.Models.ModelListener;
import com.example.myapplication.Model.Repositories.Data.Client;
import com.example.myapplication.ui.GlobalState;
import com.example.myapplication.ui.Routine.RegisterRoutineFragment;

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
    public static void editRoutine(ApolloCall.Callback<UpdateRoutineMutation.Data>listener,RoutineEntity edited,String token){

        apolloClient.mutate(
                UpdateRoutineMutation.builder().
                        description(edited.getDescription())
                        .idRoutine(edited.getId())
                        .idType(edited.getIdtype())
                        .linkPreview(edited.getLink_preview())
                        .price(edited.getPrice())
                        .name(edited.getName())
                        .token(token)

                        .build()
        ).enqueue(new RoutineGraphQLListener<UpdateRoutineMutation.Data>(listener));
    }
    public static void getResourcesByIdRoutine(ApolloCall.Callback<GetResourcesByIdRoutineMutation.Data>listener,int idRoutine,String token){
        apolloClient.mutate(GetResourcesByIdRoutineMutation.builder().idRoutine(idRoutine).token(token).build()).enqueue(new RoutineGraphQLListener<GetResourcesByIdRoutineMutation.Data>(listener));
    }

    public static void getRoutinesByIdType(ApolloCall.Callback<GetRoutinesByIdTypeQuery.Data>listener,int idType){
        apolloClient.query(GetRoutinesByIdTypeQuery.builder().idType(idType).build()).enqueue(new RoutineGraphQLListener<GetRoutinesByIdTypeQuery.Data>(listener));
    }
    public static void getRoutinesByIdUser(ApolloCall.Callback<GetUserRoutineByIdUserQuery.Data>listener,String token){
        apolloClient.query(GetUserRoutineByIdUserQuery.builder().token(token).build()).enqueue(new RoutineGraphQLListener<GetUserRoutineByIdUserQuery.Data>(listener));
    }
    public static void registerRequest(ApolloCall.Callback<RegisterRequestMutation.Data>listener,int idRoutine,String token){
        apolloClient.mutate(RegisterRequestMutation.builder().idRoutine(idRoutine).token(token).build()).enqueue(new RoutineGraphQLListener<RegisterRequestMutation.Data>(listener));
    }

    public static void registerResource(ApolloCall.Callback<RegisterResourceMutation.Data>listener, ResourceEntity resource,String token){
        apolloClient.mutate(RegisterResourceMutation.builder()
                .idRoutine(resource.getIdRoutine())
                .link(resource.getLink())
                .title(resource.getTitle())
                .position(resource.getPosition())
                .idType(resource.getIdType())
                .token(token)
                .description(resource.getDescription())

                .build()).enqueue(new RoutineGraphQLListener<RegisterResourceMutation.Data>(listener));
    }
    public static  void getRequestByIdRoutine(ApolloCall.Callback<GetRequestByIdRoutineQuery.Data>listener,int idRoutine){
        apolloClient.query(GetRequestByIdRoutineQuery.builder().idRoutine(idRoutine).build()).enqueue(new RoutineGraphQLListener<GetRequestByIdRoutineQuery.Data>(listener));
    }
    public static void registerUserRoutine(ApolloCall.Callback<RegisterUserRoutineMutation.Data>listener, UserRoutineEntity userRoutine,String token){
        apolloClient.mutate(RegisterUserRoutineMutation.builder()
                .idRoutine(userRoutine.getIdRoutine())
                .idUser(userRoutine.getIdUser())
                .idStatus(userRoutine.getIdStatus())
                .token(token)
                .build()
        ).enqueue(new RoutineGraphQLListener<RegisterUserRoutineMutation.Data>(listener));

    }
    public static void deleteRequest(ApolloCall.Callback<DeleteRequestMutation.Data>listener,int idRequest){
        apolloClient.mutate(DeleteRequestMutation.builder().idRequest(idRequest).build()).enqueue(new RoutineGraphQLListener<DeleteRequestMutation.Data>(listener));

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
