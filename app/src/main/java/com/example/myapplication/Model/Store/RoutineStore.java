package com.example.myapplication.Model.Store;

import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apollographqlandroid.GetAllTypeRoutineQuery;
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.myapplication.Model.Entities.RoutineEntity;

import java.util.List;

public class RoutineStore extends ViewModel {
    private MutableLiveData<List<GetAllTypeRoutineQuery.TypeRoutine>>listTypeRoutines;
    private MutableLiveData<List<GetRoutinesByIdOwnerQuery.Routine>>listRoutine;
    public RoutineStore(){

        this.listRoutine=new MutableLiveData<>();
        this.listTypeRoutines=new MutableLiveData<>() ;
    }
    public void setRoutines(List<GetRoutinesByIdOwnerQuery.Routine> listRoutine){
        this.listRoutine.setValue(listRoutine);
    }
    public LiveData<List<GetRoutinesByIdOwnerQuery.Routine>> getListRoutine(){
        return this.listRoutine;
    }
    public void setTypeRoutines(List<GetAllTypeRoutineQuery.TypeRoutine>typeRoutines){
        this.listTypeRoutines.setValue(typeRoutines);
    }
    public LiveData<List<GetAllTypeRoutineQuery.TypeRoutine>> getTypeRoutines(){
        return this.listTypeRoutines;
    }
}
