package com.example.myapplication.Model.Models;

import android.graphics.ColorSpace;

import com.apollographql.apollo.ApolloCall;
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

      public static void getRoutinesByIdType(ApolloCall.Callback<GetRoutinesByIdTypeQuery.Data>listener,int idType){
        RoutineRepository.getRoutinesByIdType(new ApolloCall.Callback<GetRoutinesByIdTypeQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetRoutinesByIdTypeQuery.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                listener.onFailure(e);
            }
        },idType);
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

   public static void getRoutinesByIdUser(ApolloCall.Callback<GetUserRoutineByIdUserQuery.Data>listener,String token){
        RoutineRepository.getRoutinesByIdUser(new ApolloCall.Callback<GetUserRoutineByIdUserQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetUserRoutineByIdUserQuery.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
            listener.onFailure(e);
            }
        },token);
    }
    public static void getRequestByIdRoutine(ApolloCall.Callback<GetRequestByIdRoutineQuery.Data>listener,int idRoutine){
        RoutineRepository.getRequestByIdRoutine(new ModelListener<GetRequestByIdRoutineQuery.Data>(listener),idRoutine);
    }
      public static void acceptRequest(UserRoutineEntity userRoutineEntity, int idRequest, String token){
        RoutineRepository.registerUserRoutine(new ApolloCall.Callback<RegisterUserRoutineMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<RegisterUserRoutineMutation.Data> response) {

            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        },userRoutineEntity,token);
        RoutineRepository.deleteRequest(new ApolloCall.Callback<DeleteRequestMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<DeleteRequestMutation.Data> response) {

            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        },idRequest);
    }
     public static void registerRequest(ApolloCall.Callback<RegisterRequestMutation.Data>listener,int idRoutine,String token){
        RoutineRepository.registerRequest(new ApolloCall.Callback<RegisterRequestMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<RegisterRequestMutation.Data> response) {
                listener.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
            listener.onFailure(e);
            }
        } ,idRoutine,token);
    }
     public static void rejectRequest(int idRequest){

        RoutineRepository.deleteRequest(new ApolloCall.Callback<DeleteRequestMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<DeleteRequestMutation.Data> response) {

            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        },idRequest);

    }

    private static class ModelListener<T>extends ApolloCall.Callback<T>{
        private ApolloCall.Callback<T>callback;
        public ModelListener(ApolloCall.Callback<T>listener) {
        this.callback=listener;
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
