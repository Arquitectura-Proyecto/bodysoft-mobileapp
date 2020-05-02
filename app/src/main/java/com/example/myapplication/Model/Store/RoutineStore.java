package com.example.myapplication.Model.Store;

import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apollographqlandroid.GetAllTypeRoutineQuery;
import com.example.apollographqlandroid.GetResourcesByIdRoutineMutation;
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.apollographqlandroid.GetRoutinesByIdTypeQuery;
import com.example.myapplication.Model.Entities.RoutineEntity;

import java.util.List;

public class RoutineStore extends ViewModel {
    private MutableLiveData<List<GetRoutinesByIdTypeQuery.Routine>> listSuggestedRoutines;
    private MutableLiveData<List<GetResourcesByIdRoutineMutation.Resource>> listResources;
    private MutableLiveData<GetRoutinesByIdOwnerQuery.Routine> InformationRoutine;
    private MutableLiveData<List<GetAllTypeRoutineQuery.TypeRoutine>> listTypeRoutines;
    private MutableLiveData<List<GetRoutinesByIdOwnerQuery.Routine>> listRoutine;
    private MutableLiveData<GetRoutinesByIdTypeQuery.Routine> InformationRoutinePreview;

    public RoutineStore() {
        this.InformationRoutinePreview = new MutableLiveData<>();
        this.listRoutine = new MutableLiveData<>();
        this.listTypeRoutines = new MutableLiveData<>();
        this.InformationRoutine = new MutableLiveData<>();
        this.listResources = new MutableLiveData<>();
        this.listSuggestedRoutines = new MutableLiveData<>();
    }

    public LiveData<GetRoutinesByIdTypeQuery.Routine> getInformationRoutinePreview() {
        return this.InformationRoutinePreview;
    }

    public void setInformationRoutinePreview(GetRoutinesByIdTypeQuery.Routine informationRoutinePreview) {
        this.InformationRoutinePreview.setValue(informationRoutinePreview);
    }

    public void setRoutines(List<GetRoutinesByIdOwnerQuery.Routine> listRoutine) {
        this.listRoutine.setValue(listRoutine);
    }

    public LiveData<List<GetRoutinesByIdOwnerQuery.Routine>> getListRoutine() {
        return this.listRoutine;
    }

    public void setTypeRoutines(List<GetAllTypeRoutineQuery.TypeRoutine> typeRoutines) {
        this.listTypeRoutines.setValue(typeRoutines);
    }

    public LiveData<List<GetAllTypeRoutineQuery.TypeRoutine>> getTypeRoutines() {
        return this.listTypeRoutines;
    }

    public void setRoutineInformation(GetRoutinesByIdOwnerQuery.Routine routine) {
        this.InformationRoutine.setValue(routine);
    }

    public LiveData<GetRoutinesByIdOwnerQuery.Routine> getInformationRoutine() {
        return this.InformationRoutine;
    }

    ;

    public void setListResources(List<GetResourcesByIdRoutineMutation.Resource> resources) {
        this.listResources.setValue(resources);
    }

    public LiveData<List<GetResourcesByIdRoutineMutation.Resource>> getListResources() {
        return this.listResources;
    }

    public void setListSuggestedRoutines(List<GetRoutinesByIdTypeQuery.Routine> newlistSuggestedRoutines) {
        this.listSuggestedRoutines.setValue(newlistSuggestedRoutines);
    }

    public LiveData<List<GetRoutinesByIdTypeQuery.Routine>> getListSuggestedRoutines() {
        return this.listSuggestedRoutines;
    }

}
