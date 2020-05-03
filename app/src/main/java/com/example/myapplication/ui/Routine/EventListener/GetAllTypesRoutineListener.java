package com.example.myapplication.ui.Routine.EventListener;

import android.app.Activity;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.apollographqlandroid.GetAllTypeRoutineQuery;
import com.example.myapplication.Model.Store.RoutineStore;

import org.jetbrains.annotations.NotNull;

public class GetAllTypesRoutineListener extends ApolloCall.Callback<GetAllTypeRoutineQuery.Data>{
    private RoutineStore routineStore;
    private Activity activity;
    public GetAllTypesRoutineListener(Activity activity, RoutineStore routineStore){
        this.activity=activity;
        this.routineStore=routineStore;
    }
    @Override
    public void onResponse(@NotNull Response<GetAllTypeRoutineQuery.Data> response) {
        this.activity.runOnUiThread(new Runnable() {
            @Override public void run() {
                routineStore.setTypeRoutines(response.data().TypeRoutines());
            }
        });
    }

    @Override
    public void onFailure(@NotNull ApolloException e) {

    }
}