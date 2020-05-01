package com.example.myapplication.Model.Models;

import android.graphics.ColorSpace;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.CrearRutinaMutation;
import com.example.apollographqlandroid.GetAllTypeRoutineQuery;
import com.example.apollographqlandroid.GetResourcesByIdRoutineMutation;
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.apollographqlandroid.GetRoutinesQuery;
import com.example.apollographqlandroid.RegisterResourceMutation;
import com.example.apollographqlandroid.UpdateRoutineMutation;
import com.example.myapplication.Model.Entities.ResourceEntity;
import com.example.myapplication.Model.Entities.RoutineEntity;
import com.example.myapplication.Model.Repositories.RoutineRepository;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RoutineModel {
    private static RoutineRepository routineRepository;

    public RoutineModel(){}

    public static  void getAllRoutines(ApolloCall.Callback<GetRoutinesQuery.Data>listener){

        RoutineRepository.getAllRoutines(new ApolloCall.Callback<GetRoutinesQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetRoutinesQuery.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    public static void  getAllRoutinesByIdOwner(ApolloCall.Callback<GetRoutinesByIdOwnerQuery.Data>listener, String token){
        ApolloCall.Callback<GetRoutinesByIdOwnerQuery.Data>ModelListener=new ApolloCall.Callback<GetRoutinesByIdOwnerQuery.Data>() {

            @Override
            public void onResponse(@NotNull Response<GetRoutinesByIdOwnerQuery.Data> response) {
            //List<RoutineEntity>routines=new ArrayList<>();
            //RoutineEntity routineEntity;
            //response.data().Routines().get(1).type().fragments().type().
              listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                listener.onFailure(e);
            }
        };
        RoutineRepository.getRoutinesByIdOwner(ModelListener,token);
}


    public static void getAllTypeRoutine(ApolloCall.Callback<GetAllTypeRoutineQuery.Data>listener){
        RoutineRepository.getAllTypeRoutine(new ApolloCall.Callback<GetAllTypeRoutineQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetAllTypeRoutineQuery.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
            listener.onFailure(e);
            }
        });
    }
    public static void RegisterRoutine(ApolloCall.Callback<CrearRutinaMutation.Data>listener,RoutineEntity newroutineEntity,String token){
        RoutineRepository.registerRoutine(new ApolloCall.Callback<CrearRutinaMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<CrearRutinaMutation.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
            listener.onFailure(e);
            }
        },newroutineEntity,token);
    }
    public static void EditRoutine(ApolloCall.Callback<UpdateRoutineMutation.Data>listener,RoutineEntity editedRoutine,String token){
        RoutineRepository.editRoutine(new ApolloCall.Callback<UpdateRoutineMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<UpdateRoutineMutation.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
            listener.onFailure(e);
            }
        },editedRoutine,token);



    }
    public static void getResourcesByIdRoutine(ApolloCall.Callback<GetResourcesByIdRoutineMutation.Data>listener,int idRoutine,String token){
        RoutineRepository.getResourcesByIdRoutine(new ApolloCall.Callback<GetResourcesByIdRoutineMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetResourcesByIdRoutineMutation.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
            listener.onFailure(e);
            }
        },idRoutine,token);

    }
    public static void registerResource(ApolloCall.Callback<RegisterResourceMutation.Data>listener, ResourceEntity resource,String token){

        RoutineRepository.registerResource(new ApolloCall.Callback<RegisterResourceMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<RegisterResourceMutation.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
            listener.onFailure(e);
            }
        },resource,token);

    }
}
