package com.example.myapplication.Model.Models;

import android.graphics.ColorSpace;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.apollographqlandroid.GetRoutinesQuery;
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
}
