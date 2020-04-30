package com.example.myapplication.Model.Store;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apollographqlandroid.GetCurrentbyIdQuery;

import java.util.ArrayList;
import java.util.List;

public class SessionStore extends ViewModel {

    /*
    private MutableLiveData<List<GetCurrentbyIdQuery.Schedule>>listRoutine;
    private MutableLiveData<List<GetCurrentbyIdQuery.Schedule>>CurrentRoutine;
    private MutableLiveData<List<GetCurrentbyIdQuery.Schedule>>AvaibleRutine;

    public SessionStore(){
        this.listRoutine=new MutableLiveData<>();
        this.CurrentRoutine=new MutableLiveData<>();
        this.AvaibleRutine=new MutableLiveData<>();
    }

    public void setSession(List<GetCurrentbyIdQuery.Schedule> listRoutine){
        this.listRoutine.setValue(listRoutine);
        this.AvaibleRutine.setValue(getbystatus(1,listRoutine));
        this.CurrentRoutine.setValue(getbystatus(2,listRoutine));
    }

    public LiveData<List<GetCurrentbyIdQuery.Schedule>> getListRoutine(){
        return this.listRoutine;
    }
    public LiveData<List<GetCurrentbyIdQuery.Schedule>> getCurrentRoutine(){
        return this.CurrentRoutine;
    }
    public LiveData<List<GetCurrentbyIdQuery.Schedule>> getAvaibleRutine(){
        return this.AvaibleRutine;
    }

    private List<GetCurrentbyIdQuery.Schedule> getbystatus (int status,List<GetCurrentbyIdQuery.Schedule> listA ){
        List<GetCurrentbyIdQuery.Schedule> list = new ArrayList();

        for (GetCurrentbyIdQuery.Schedule schedule:listA) {
            if(schedule.getStatus().getIdStatus()==status){
                list.add(schedule);
            }
        }
        return list;
    }*/

}
